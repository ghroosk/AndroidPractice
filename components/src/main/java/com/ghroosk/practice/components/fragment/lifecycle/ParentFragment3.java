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
public class ParentFragment3 extends LazyFragment {

    private static final String TAG = ParentFragment3.class.getSimpleName();
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
        super.onAttach(context);
        Log.e(TAG, "onAttach: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint isVisibleToUser: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG, "onHiddenChanged hidden: " + hidden);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
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
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: ");
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
