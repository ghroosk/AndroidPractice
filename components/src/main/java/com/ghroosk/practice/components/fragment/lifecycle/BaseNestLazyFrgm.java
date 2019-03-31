package com.ghroosk.practice.components.fragment.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

/**
 * 嵌套 懒加载 Fragment
 * Created by ghroosk on 2019/3/31.
 */
public class BaseNestLazyFrgm extends Fragment {

    private static final String TAG = BaseNestLazyFrgm.class.getSimpleName();

    // 是否首次可见，第一次创建的标志位.
    private boolean mIsFirstVisible = true;

    // 是否已执行 onActivityCreated 创建视图.
    private boolean mIsViewCreated = false;

    // 当前是否可见.
    private boolean mCurrentVisible = false;

    /**
     * 走这里分发可见状态情况有两种:
     * <li>1. 已缓存的 Fragment 被展示的时候 .
     * <li>2. 当前 Fragment 由可见变成不可见的状态时.<br>
     * 对于默认 Fragment 和间隔 Fragment 需要等到 isViewCreated = true 后才可以通过此分发用户可见<br>
     * 这种情况下第一次可见不是在这里分发的.
     * 因为 isViewCreated = false 成立，可见状态在 onActivityCreated 中分发<br>
     * 对于非默认 Fragment，View 创建完成 isViewCreated = true 成立，走这里分发可见状态.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mIsViewCreated) {
            if (isVisibleToUser && !mCurrentVisible) {
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && mCurrentVisible) {
                dispatchUserVisibleHint(false);
            } else {
                Log.d(TAG, "setUserVisibleHint: ");
            }
        }
    }

    /**
     * 对于非默认 Fragment 或者非默认显示的 Fragment 在该生命周期中只做了 isViewCreated 标志位设置.<br>
     * 对于默认 Fragment，getUserVisibleHint() = true 并且 !isHidden() = true 时，
     * 在这里第一次分发用户可见.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewCreated = true;
        // !isHidden() 默认为 true  在调用 hide show 的时候可以使用，
        // 但需要注意处理子 fragment 第一次创建的情况；
        if (!isHidden() && getUserVisibleHint()) {
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            dispatchUserVisibleHint(false);
        } else {
            dispatchUserVisibleHint(true);
        }
    }

    /**
     * 当前页面 Activity 可见时所有缓存的 Fragment 都会回调 onResume,
     * 对于可见状态的生命周期调用顺序，父 Fragment总是优先于子 Fragment.<br>
     * 所以当第一次的 onResume 时我们不作任何处理，可以通过 !mIsFirstVisible 保证这一点；<br>
     * 此外我们还需要判定哪个 Fragment 位于可见状态通知用户在当前页面是再次可见的状态。
     */
    @Override
    public void onResume() {
        super.onResume();
        if (!mIsFirstVisible) {
            if (!isHidden() && !mCurrentVisible && getUserVisibleHint()) {
                dispatchUserVisibleHint(true);
            }
        }
    }

    /**
     * 当用户进入其他界面的时候所有的缓存的 Fragment 都会 onPause，
     * 而对于不可见事件，内部的 Fragment 生命周期总是先于外层 Fragment.<br>
     * 但是我们想要知道只是当前可见的的 Fragment 不可见状态，
     * currentVisibleState && getUserVisibleHint() 能够限定是当前可见的 Fragment.
     */
    @Override
    public void onPause() {
        super.onPause();
        if (mCurrentVisible && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    /**
     * 统一分派当前 Fragment 可见状态：显示和隐藏
     *
     * @param visible 是否可见
     */
    private void dispatchUserVisibleHint(boolean visible) {
        // 当前 Fragment 是 child 的时候且当父 fragment 不可见时直接 return 掉
        // 这里限制则可以限制多层嵌套的时候子 Fragment 的分发
        if (visible && isParentInvisible()) {
            return;
        }

        // 此处是对子 Fragment 重复调用不可见的限制，因为子 Fragment 先于父 Fragment回调 onPause
        // 当父 dispatchChildVisibleState 的时候第二次回调本方法无需再处理
        if (mCurrentVisible == visible) {
            return;
        }

        mCurrentVisible = visible;

        if (visible) {
            if (mIsFirstVisible) {
                mIsFirstVisible = false;
                onFragmentFirstVisible();
                // 如果不想重复再调用 onFragmentResume() 则 return；
                return;

            }
            onFragmentResume();
            // 对于可见状态的生命周期调用顺序，父 Fragment总是优先于子 Fragment.
            dispatchChildVisibleState(true);
        } else {
            // 对于不可见事件，内部的 Fragment 生命周期总是先于外层 Fragment
            dispatchChildVisibleState(false);
            onFragmentPause();
        }
    }

    /**
     * 用于分发可见时间的时候父获取 fragment 是否隐藏
     *
     * @return true fragment 不可见， false 父 fragment 可见
     */
    private boolean isParentInvisible() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof BaseNestLazyFrgm) {
            BaseNestLazyFrgm fragment = (BaseNestLazyFrgm) parentFragment;
            return !fragment.isSupportVisible();
        } else {
            return false;
        }
    }

    private boolean isSupportVisible() {
        return mCurrentVisible;
    }

    /**
     * 当前 Fragment 为 child 且可见时，由于父 Fragment 变成不可见状态后，
     * 需要通知子 Fragment 我不可见，你也不可见.<br>
     * 当父 Fragment 变成可见状态后，也需要通知子 Fragment 变成可见状态
     */
    private void dispatchChildVisibleState(boolean visible) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        List<Fragment> fragments = childFragmentManager.getFragments();
        if (!fragments.isEmpty()) {
            for (Fragment child : fragments) {
                if (child instanceof BaseNestLazyFrgm && !child.isHidden() && child.getUserVisibleHint()) {
                    ((BaseNestLazyFrgm) child).dispatchUserVisibleHint(visible);
                }
            }
        }
    }

    protected void onFragmentFirstVisible() {
    }

    protected void onFragmentResume() {
    }

    protected void onFragmentPause() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsViewCreated = false;
        mIsFirstVisible = true;
    }
}
