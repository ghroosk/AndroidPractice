package com.ghroosk.practice.components.fragment.lifecycle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * 未嵌套 懒加载Fragment
 * Created by ghroosk on 2018/1/5.
 */

public class LazyFragment extends Fragment {

    private static final String TAG = LazyFragment.class.getSimpleName();

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
            // current invisible and isVisibleToUser is true
            if (isVisibleToUser && !mCurrentVisible) {
                Log.e(TAG, "dispatchUserVisibleHint setUserVisibleHint: true" );
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && mCurrentVisible) {
                Log.e(TAG, "dispatchUserVisibleHint setUserVisibleHint: false" );
                dispatchUserVisibleHint(false);
            } else {
                Log.d(TAG, "setUserVisibleHint: no need to change");
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 这里的判断跟 setUserVisibleHint() 一致
        if (hidden) {
            Log.e(TAG, "dispatchUserVisibleHint onHiddenChanged: false" );
            dispatchUserVisibleHint(false);
        } else {
            Log.e(TAG, "dispatchUserVisibleHint onHiddenChanged: true" );
            dispatchUserVisibleHint(true);
        }
    }

    /**
     * 对于非默认 Fragment 或者非默认显示的 Fragment 在该生命周期中只做了 isViewCreated 标志位设置.<br>
     * 对于默认 Fragment，getUserVisibleHint() = true 并且 !isHidden() = true 时，
     * 在这里第一次分发用户可见.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
        if (getUserVisibleHint() && !isHidden()) {
            Log.e(TAG, "dispatchUserVisibleHint onViewCreated: true" );
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
            if (!mCurrentVisible && getUserVisibleHint() && !isHidden()) {
                Log.e(TAG, "dispatchUserVisibleHint onResume: true" );
                dispatchUserVisibleHint(true);
            }
        }
    }

    /**
     * 当用户进入其他界面的时候所有的缓存的 Fragment 都会 onPause，
     * 而对于不可见事件，内部的 Fragment 生命周期总是先于外层 Fragment
     * 但是我们想要知道只是当前可见的的 Fragment 不可见状态，
     * currentVisibleState && getUserVisibleHint() 能够限定是当前可见的 Fragment
     */
    @Override
    public void onPause() {
        super.onPause();
        if (mCurrentVisible && getUserVisibleHint()) {
            Log.e(TAG, "dispatchUserVisibleHint onPause: false" );
            dispatchUserVisibleHint(false);
        }
    }

    /**
     * 统一分派当前 Fragment 可见状态：显示和隐藏
     * @param visible 是否可见
     */
    private void dispatchUserVisibleHint(boolean visible) {
        mCurrentVisible = visible;
        if (mCurrentVisible) {
            if (mIsFirstVisible) {
                mIsFirstVisible = false;
                onFragmentFirstVisible();
            }
            onFragmentResume();
        } else {
            onFragmentPause();
        }
    }

    /**
     * 对用户第一次可见
     */
    public void onFragmentFirstVisible() {
//        Log.d(TAG, "onFragmentFirstVisible: ");
    }

    /**
     * 对用户可见
     */
    public void onFragmentResume() {
//        Log.d(TAG, "onFragmentResume: ");
    }

    /**
     * 对用户不可见
     */
    public void onFragmentPause() {
//        Log.d(TAG, "onFragmentPause: ");
    }

    @Override
    public void onDestroyView() {
        mIsFirstVisible = true;
        mIsViewCreated = false;
        super.onDestroyView();
    }

}
