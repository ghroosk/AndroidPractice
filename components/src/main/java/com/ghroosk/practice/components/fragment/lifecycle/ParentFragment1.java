package com.ghroosk.practice.components.fragment.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghroosk.practice.components.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhx on 2019/3/12.
 */
public class ParentFragment1 extends LazyFragment {

    private static final String TAG = ParentFragment1.class.getSimpleName();
    private Context mContext;
    private static final int DISPATCH = 0;
    private static final int WAIT_FOR_START = 1;
    private static final int IN_TRANSIT = 2;
    private static final int RECEIPT = 3;

    private int mLastChecked = DISPATCH;
    private List<Fragment> mFragmentList;
    public TextView[] mTvArrays;
    public View[] mTipViewArrays;
    public ViewPager mViewPager;
    private View mView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_childer_lifecycle, container, false);
//        mTextView = mView.findViewById(R.id.text);
//        mTextView.setText(TAG);
        initView();

        initData();
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: ");
        super.onAttach(context);
        mContext = context;
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

    private void initView() {
        initWidget();

        initFragment();

        initViewPaper();

    }

    private void initData() {
        selectChange(0);
    }

    private void initWidget() {
        mTvArrays = new TextView[4];
        mTvArrays[0] = mView.findViewById(R.id.dispatch);
        mTvArrays[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(DISPATCH);
            }
        });
        mTvArrays[1] = mView.findViewById(R.id.wait_for_start);
        mTvArrays[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(WAIT_FOR_START);
            }
        });
        mTvArrays[2] = mView.findViewById(R.id.in_transit);
        mTvArrays[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(IN_TRANSIT);
            }
        });
        mTvArrays[3] = mView.findViewById(R.id.receipt);
        mTvArrays[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(RECEIPT);
            }
        });

        mTipViewArrays = new View[4];
        mTipViewArrays[0] = mView.findViewById(R.id.dispatch_tip);
        mTipViewArrays[1] = mView.findViewById(R.id.wait_for_start_tip);
        mTipViewArrays[2] = mView.findViewById(R.id.in_transit_tip);
        mTipViewArrays[3] = mView.findViewById(R.id.receipt_tip);

        mViewPager = mView.findViewById(R.id.task_viewpager);
    }

    private void initFragment() {
        ChildrenFragment1 childrenFragment1 = new ChildrenFragment1();
        ChildrenFragment2 childrenFragment2 = new ChildrenFragment2();
        ChildrenFragment3 childrenFragment3 = new ChildrenFragment3();
        ChildrenFragment4 childrenFragment4 = new ChildrenFragment4();

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(childrenFragment1);
        mFragmentList.add(childrenFragment2);
        mFragmentList.add(childrenFragment3);
        mFragmentList.add(childrenFragment4);

        //如果transaction  commit（）过  那么我们要重新得到transaction
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.commit();
    }

    private void initViewPaper() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(fragmentAdapter);

        mViewPager.setOffscreenPageLimit(mTvArrays.length);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int select) {
                if (select == DISPATCH) {
                    selectChange(DISPATCH);

                } else if (select == WAIT_FOR_START) {
                    selectChange(WAIT_FOR_START);

                } else if (select == IN_TRANSIT) {
                    selectChange(IN_TRANSIT);

                } else if (select == RECEIPT) {
                    selectChange(RECEIPT);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void selectChange(int selectItem) {
        mViewPager.setCurrentItem(selectItem, true);
        clickTabbar(selectItem);
    }

    private void clickTabbar(int currentCheck) {
        if (currentCheck != mLastChecked) {
            mTvArrays[currentCheck].setTextColor(ContextCompat.getColor(mContext, R.color.main_color));
            mTvArrays[mLastChecked].setTextColor(ContextCompat.getColor(mContext, R.color.text_content_3));
            mTipViewArrays[currentCheck].setBackgroundColor(ContextCompat.getColor(mContext, R.color.main_color));
            mTipViewArrays[mLastChecked].setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            mLastChecked = currentCheck;
        }
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
    public void onFragmentFirstVisible() {
        Log.d(TAG, "onFragmentFirstVisible: ");
    }

    /**
     * 对用户可见
     */
    @Override
    public void onFragmentResume() {
        Log.d(TAG, "onFragmentResume: ");
    }

    /**
     * 对用户不可见
     */
    @Override
    public void onFragmentPause() {
        Log.d(TAG, "onFragmentPause: ");
    }

}
