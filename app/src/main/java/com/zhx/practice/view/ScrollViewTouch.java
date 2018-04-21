package com.zhx.practice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * touch event log
 * Created by zhx on 2018/4/20.
 */

public class ScrollViewTouch extends ScrollView {

    private static final String TAG = ScrollViewTouch.class.getSimpleName();

    public ScrollViewTouch(Context context) {
        super(context);
    }

    public ScrollViewTouch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewTouch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final int actionMasked = ev.getActionMasked();

        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "onTouchEvent: ACTION_CANCEL");
            default:
                break;

        }
        return super.onTouchEvent(ev);
    }
}
