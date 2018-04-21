package com.zhx.practice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * TextView Touch
 * Created by zhx on 2018/4/20.
 */

class EditTextTouch extends android.support.v7.widget.AppCompatEditText {

    private static final String TAG = EditTextTouch.class.getSimpleName();

    public EditTextTouch(Context context) {
        super(context);
    }

    public EditTextTouch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextTouch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        MotionEvent vtev = MotionEvent.obtain(ev);

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
