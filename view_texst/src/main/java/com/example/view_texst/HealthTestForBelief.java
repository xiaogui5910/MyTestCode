package com.example.view_texst;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26.
 */
public class HealthTestForBelief extends View {

    private Paint paint;
    private int mMeasureWidth;
    private int mMeasureHeight;
    private int mViewBottom;
    private int mAverageHeight;
    private int mAverageWidth;
    private String[] text = {"0", "5", "10", "15", "20"};
    private ArrayList<DrugUseTest> al;
    private float startX;
    private float startY;
    private int mViewLeft;
    private int mViewRight;
    private float stopX;
    private float mBaseLeft;
    private float downX;
    private float downY;
    private int mViewTop;
    private float totalDix = 0;
    private float mLastPosition;
    private int everyScoreHeight;
    private float lastX;
    private float lastY;
    private String mTableName;
    private int BWScreenHeight;
    private int BWScreenWidth;
    private OnClicked onClicked;
    private long mDownTime;
    private long mUpTime;

    public HealthTestForBelief(Context context) {
        this(context, null);
    }

    public HealthTestForBelief(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public HealthTestForBelief(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        mViewBottom = CommonUtils.getDimens(R.dimen.view_test_height);
        startX = (mMeasureWidth - mMeasureWidth / 25) / 7;
        startY = mViewBottom - mViewBottom / 7;
        stopX = (mMeasureWidth - mMeasureWidth / 25) - (mMeasureWidth - mMeasureWidth / 25) / 24;
        mBaseLeft = startX;
        setMeasuredDimension(BWScreenWidth, BWScreenHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    public void setWidthHeight(int width, int height) {
        this.BWScreenHeight = height;
        this.BWScreenWidth = width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e("onDraw ", "onDraw");
        drawBackground(canvas);
        if (al != null && al.size() > 0) {
            drawData(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                mDownTime = System.currentTimeMillis();
                if (downY > mViewBottom || downY < mViewTop) {
                    ToastUtil.showToast("过界了");
                    return super.onTouchEvent(event);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float dix = moveX - downX;
                totalDix = dix + totalDix;


                if (mBaseLeft >= startX && dix > 0) {
                    ToastUtil.showToast("这是最左边了");
                    return super.onTouchEvent(event);
                }
                if (dix < 0 && mLastPosition < stopX) {
                    ToastUtil.showToast("这是最右边了");
                    return super.onTouchEvent(event);
                }

                mBaseLeft = mBaseLeft + dix;
                Log.e("mBaseLeft=", mBaseLeft + "");
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                mUpTime = System.currentTimeMillis();
                long timeDix = mUpTime - mDownTime;
                float dix1 = event.getX()-downX;
                if (dix1 <= 0.01&&dix1>=-0.01&&timeDix<0.1){
                    if (onClicked != null){
                        onClicked.onClicked();
                    }
                    return true;
                }

                break;
        }
        invalidate();
        return true;
    }

    /**
     * 画横坐标和数据
     *
     * @param canvas
     */

    private void drawData(Canvas canvas) {


        for (int i = 0; i < al.size(); i++) {
            //画横坐标和线
            String date = al.get(i).getDate();
            int score = al.get(i).getScore();
            everyScoreHeight = mAverageHeight / 5;
            float centerCircleY = startY - everyScoreHeight * score;

            String month = date.substring(4, 6);
            String day = date.substring(6);
            float left = mBaseLeft + i * mAverageWidth;
            if (left <= stopX && left >= startX) {
                paint.setColor(Color.WHITE);

                canvas.drawText(month + "/" + day, mBaseLeft + i * mAverageWidth - (mAverageWidth / 3), startY + mAverageHeight * 2 / 3, paint);
                paint.setStrokeWidth(1);
                paint.setColor(Color.WHITE);
                canvas.drawLine(left, startY, left, startY - 4 * mAverageHeight, paint);

                //画点和线
                canvas.drawCircle(left, centerCircleY, 10, paint);
                paint.setColor(Color.parseColor("#4F81BD"));

                canvas.drawCircle(left, centerCircleY, 4, paint);
                if (i >= 1 && lastX >= startX) {
                    paint.setColor(Color.WHITE);
                    paint.setStrokeWidth(2);
                    canvas.drawLine(lastX, lastY, left, centerCircleY, paint);
                }


            }
            lastX = left;
            lastY = centerCircleY;
            if (i + 1 == al.size()) {
                mLastPosition = left;
            }


        }
    }

    /**
     * 画背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        paint.setColor(Color.parseColor("#4F81BD"));
        mAverageHeight = mViewBottom / 7;
        mAverageWidth = (mMeasureWidth - mMeasureWidth / 25) / 7;
        mViewLeft = mMeasureWidth / 50;
        mViewRight = mMeasureWidth - mMeasureWidth / 50;
        mViewTop = 0;

        Rect rect = new Rect(mViewLeft, mViewTop, mViewRight, mViewBottom);
        canvas.drawRect(rect, paint);

        float stopY = startY;
        paint.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(startX, startY - i * mAverageHeight, stopX, startY - i * mAverageHeight, paint);
        }
        canvas.drawLine(startX, startY - 4 * mAverageHeight, startX, startY, paint);

        canvas.drawLine(stopX, startY - 4 * mAverageHeight, stopX, startY, paint);
        paint.setTextSize(CommonUtils.getDimens(R.dimen.view_test_text_size));
        //花标题
        paint.setStrokeWidth(4);
        mTableName = "老年人用药信念测评汇总图";
        canvas.drawText(mTableName, startX / 2, startY - 5 * mAverageHeight, paint);
        //画纵坐标

        paint.setTextSize(CommonUtils.getDimens(R.dimen.view_test_text_size2));
        paint.setStrokeWidth(2);
        for (int i = 0; i < 5; i++) {
            canvas.drawText(text[i], startX / 2, startY - i * mAverageHeight, paint);
        }
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(ArrayList<DrugUseTest> data) {
        al = data;
    }

    public void setStyle(int testType) {
        switch (testType) {
            case 1:
                mTableName = "老年人合理用药知识测评结果列表";
                break;
            case 2:
                mTableName = "老年人用药信念测评结果列表";
                break;
            case 3:
                mTableName = "老年人用药依存性测评结果列表";
                break;
        }
        invalidate();
    }

    public void setOnClicked(OnClicked onClicked){
        this.onClicked = onClicked;
    }

    public interface OnClicked{
        void onClicked();
    }
}


