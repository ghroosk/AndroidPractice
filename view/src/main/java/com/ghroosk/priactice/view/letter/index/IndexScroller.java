package com.ghroosk.priactice.view.letter.index;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 字母列表
 * Created by zhx on 2019/3/9.
 */
public class IndexScroller extends View {

    /*绘制的列表导航字母*/
    private final String mWorks[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    /*字母画笔*/
    private Paint mLetterPaint;

    /*字母背景画笔*/
    private Paint mBackgroundPaint;

    /*每一个字母的宽度*/
    private int mLetterWidth;

    /*每一个字母的高度*/
    private int mLetterHeight;

    /*手指按下的字母索引*/
    private int mTouchIndex = 0;

    /*手指按下的字母改变接口*/
    private onWordsChangeListener mWordsChangeListener;

    public IndexScroller(Context context) {
        super(context);
        init();
    }

    public IndexScroller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexScroller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mLetterPaint = new Paint();
        mBackgroundPaint = new Paint();

        mLetterPaint.setTextSize(32);
        mBackgroundPaint.setColor(Color.RED);
        mLetterPaint.setAntiAlias(true);
        mBackgroundPaint.setAntiAlias(true);
//        paint.setStrokeWidth(5)：设置画笔宽度
//        paint.setAntiAlias(true)：设置是否使用抗锯齿功能，如果使用，会导致绘图速度变慢
//        paint.setStyle(Paint.Style.FILL)：设置绘图样式，对于设置文字和几何图形都有效，可取值有三种 ：
// 1、Paint.Style.FILL：填充内部 2、Paint.Style.FILL_AND_STROKE：填充内部和描边 3、Paint.Style.STROKE：仅描边
//        paint.setTextAlign(Align.CENTER)：设置文字对齐方式
//        paint.setTextSize(12)：设置文字大小
    }

    //得到画布的宽度和每一个字母所占的高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLetterWidth = getMeasuredWidth();
        //使得边距好看一些
        int height = getMeasuredHeight() - 10;
        mLetterHeight = height / 26;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = Math.min(mLetterWidth / 2, mLetterHeight / 2);
        for (int i = 0, length = mWorks.length; i < length; i++) {
            //判断是不是我们按下的当前字母
            if (mTouchIndex == i) {
                //绘制文字圆形背景
                canvas.drawCircle(mLetterWidth / 2, mLetterHeight / 2 + i * mLetterHeight + 10,
                        radius, mBackgroundPaint);
                mLetterPaint.setColor(Color.WHITE);
            } else {
                mLetterPaint.setColor(Color.GRAY);
            }
            //获取文字的宽高
            Rect rect = new Rect();
            mLetterPaint.getTextBounds(mWorks[i], 0, 1, rect);
            int wordWidth = rect.width();
//            int wordHeight = rect.height();
            //绘制字母
            float wordX = mLetterWidth / 2 - wordWidth / 2;
            float wordY = mLetterWidth / 2 + i * mLetterHeight + 10;
//            float wordY = (mLetterHeight / 2 - wordHeight / 2) + i * mLetterHeight;
            canvas.drawText(mWorks[i], wordX, wordY, mLetterPaint);

        }
    }

    /**
     * 当手指触摸按下的时候改变字母背景颜色
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //关键点===获得我们按下的是那个索引(字母)
                int index = (int) (y / mLetterHeight);
                if (index != mTouchIndex)
                    mTouchIndex = index;
                //防止数组越界
                if (mWordsChangeListener != null && 0 <= mTouchIndex && mTouchIndex <= mWorks.length - 1) {
                    //回调按下的字母
                    mWordsChangeListener.wordsChange(mWorks[mTouchIndex]);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起,不做任何操作
                break;
        }
        return true;
    }

    /*设置当前按下的是那个字母*/
    public void setTouchIndex(String word) {
        for (int i = 0; i < mWorks.length; i++) {
            if (mWorks[i].equals(word)) {
                mTouchIndex = i;
                invalidate();
                return;
            }
        }
    }

    /**
     * 手指按下了哪个字母的回调接口
     */
    public interface onWordsChangeListener {
        void wordsChange(String words);
    }

    /**
     * 设置手指按下字母改变监听
     */
    public void setOnWordsChangeListener(onWordsChangeListener listener) {
        this.mWordsChangeListener = listener;
    }

}
