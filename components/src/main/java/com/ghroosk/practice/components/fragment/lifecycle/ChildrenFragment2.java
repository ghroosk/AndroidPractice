package com.ghroosk.practice.components.fragment.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghroosk.practice.components.R;

/**
 * Created by zhx on 2019/3/12.
 */
public class ChildrenFragment2 extends LazyFragment {

    private static final String TAG = ChildrenFragment2.class.getSimpleName();
    private View mView;
    private TextView mTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_lifecycle, container, false);
        mTextView = mView.findViewById(R.id.text);
        mTextView.setText(TAG);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "setUserVisibleHint isVisibleToUser: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.e(TAG, "onHiddenChanged hidden: " + hidden);
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    /**
     * 对用户第一次可见
     */
    @Override
    public void onFragmentFirstVisible(){
        Log.d(TAG, "onFragmentFirstVisible: ");
    }

    /**
     *   对用户可见
     */
    @Override
    public void onFragmentResume(){
        Log.d(TAG, "onFragmentResume: ");
    }

    /**
     *  对用户不可见
     */
    @Override
    public void onFragmentPause(){
        Log.d(TAG, "onFragmentPause: ");
    }

}
