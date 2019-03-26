package com.ghroosk.practice.components.fragment.lifecycle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * 未嵌套 懒加载Fragment
 * Created by zhx on 2018/1/5.
 */

public class LazyFragment extends Fragment {

    private static final String TAG = LazyFragment.class.getSimpleName();

    // 是否首次可见，第一次创建的标志位.
    private boolean mIsFirstVisible = true;

    // 是否已执行 onActivityCreated 创建视图.
    private boolean mIsViewCreated = false;

    // 当前是否可见.
    private boolean mCurrentVisible = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mIsViewCreated) {
            // current invisible and isVisibleToUser to true
            if (isVisibleToUser && !mCurrentVisible) {
                dispatchUserHint(true);
            } else if (!isVisibleToUser && mCurrentVisible) {
                dispatchUserHint(false);
            } else {
                Log.d(TAG, "setUserVisibleHint: no need to change");
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            dispatchUserHint(false);
        }else {
            dispatchUserHint(true);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mIsFirstVisible) {
            if (!mCurrentVisible && getUserVisibleHint() && !isHidden()) {
                dispatchUserHint(true);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mCurrentVisible && getUserVisibleHint()) {
            dispatchUserHint(false);
        }
    }

    private void dispatchUserHint(boolean visible) {
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
    public void onFragmentFirstVisible(){
//        Log.d(TAG, "onFragmentFirstVisible: ");
    }

    /**
     *   对用户可见
     */
    public void onFragmentResume(){
//        Log.d(TAG, "onFragmentResume: ");
    }

    /**
     *  对用户不可见
     */
    public void onFragmentPause(){
//        Log.d(TAG, "onFragmentPause: ");
    }

    @Override
    public void onDestroyView() {
        mIsFirstVisible = true;
        mIsViewCreated = false;
        super.onDestroyView();
    }

}
