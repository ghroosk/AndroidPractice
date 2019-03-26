package com.ghroosk.practice.components.fragment.lifecycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ghroosk.practice.components.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhx on 2019/3/12.
 */
public class FragmentAct extends AppCompatActivity {

    private static final int DISPATCH = 0;
    private static final int WAIT_FOR_START = 1;
    private static final int IN_TRANSIT = 2;
    private static final int RECEIPT = 3;

    private int mLastChecked = DISPATCH;
    private List<Fragment> mFragmentList;
    public TextView[] mTvArrays;
    public View[] mTipViewArrays;
    public ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initView();

        initData();
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
        mTvArrays[0] = findViewById(R.id.dispatch);
        mTvArrays[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(DISPATCH);
            }
        });
        mTvArrays[1] = findViewById(R.id.wait_for_start);
        mTvArrays[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(WAIT_FOR_START);
            }
        });
        mTvArrays[2] = findViewById(R.id.in_transit);
        mTvArrays[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(IN_TRANSIT);
            }
        });
        mTvArrays[3] = findViewById(R.id.receipt);
        mTvArrays[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectChange(RECEIPT);
            }
        });

        mTipViewArrays = new View[4];
        mTipViewArrays[0] = findViewById(R.id.dispatch_tip);
        mTipViewArrays[1] = findViewById(R.id.wait_for_start_tip);
        mTipViewArrays[2] = findViewById(R.id.in_transit_tip);
        mTipViewArrays[3] = findViewById(R.id.receipt_tip);

        mViewPager = findViewById(R.id.task_viewpager);
    }

    private void initFragment() {
        ParentFragment1 parentFragment1 = new ParentFragment1();
        ParentFragment2 parentFragment2 = new ParentFragment2();
        ParentFragment3 parentFragment3  = new ParentFragment3();
        ParentFragment4 parentFragment4 = new ParentFragment4();

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(parentFragment1);
        mFragmentList.add(parentFragment2);
        mFragmentList.add(parentFragment3);
        mFragmentList.add(parentFragment4);

        //如果transaction  commit（）过  那么我们要重新得到transaction
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.commit();
    }

    private void initViewPaper() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
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
            mTvArrays[currentCheck].setTextColor(ContextCompat.getColor(this, R.color.main_color));
            mTvArrays[mLastChecked].setTextColor(ContextCompat.getColor(this, R.color.text_content_3));
            mTipViewArrays[currentCheck].setBackgroundColor(ContextCompat.getColor(this, R.color.main_color));
            mTipViewArrays[mLastChecked].setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            mLastChecked = currentCheck;
        }
    }

}
