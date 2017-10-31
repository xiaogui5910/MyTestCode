package com.example.lenovo.mytestcode.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 仿postman的加载自定义图标
 * Created by lenovo on 2017/7/26.
 */

public class PostManView extends View {

  private int mRadius = 40;  //初始圆半径系数
  private int mInterval = 50; //每个圆的间隔
  private Paint mPaintCircle; //画笔-圆圈
  private Paint mPaintPoint;  //画笔-圆点
  //第一个小圆
  private float X1;
  private float Y1;
  //第二个小圆
  private float X2;
  private float Y2;
  //第三个小圆
  private float X3;
  private float Y3;

  public PostManView(Context context) {
    super(context);
    init();
  }

  public PostManView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public PostManView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPaintCircle = new Paint();
    mPaintCircle.setAntiAlias(true);
    mPaintCircle.setColor(Color.RED);
    mPaintCircle.setStyle(Paint.Style.STROKE);
    mPaintCircle.setStrokeWidth(3);

    mPaintPoint = new Paint();
    mPaintPoint.setColor(Color.RED);
    mPaintPoint.setAntiAlias(true);
    mPaintPoint.setStyle(Paint.Style.FILL);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int mWidth = MeasureSpec.getSize(widthMeasureSpec);
    setMeasuredDimension(mWidth, mWidth);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;
    canvas.drawCircle(centerX, centerY, mRadius + mInterval, mPaintCircle);
    canvas.drawCircle(centerX, centerY, mRadius + mInterval * 2, mPaintCircle);
    canvas.drawCircle(centerX, centerY, mRadius + mInterval * 3, mPaintCircle);

    canvas.drawCircle(centerX + X1, centerY + Y1, 20, mPaintPoint);
    canvas.drawCircle(centerX + X2, centerY + Y2, 20, mPaintPoint);
    canvas.drawCircle(centerX + X3, centerY + Y3, 20, mPaintPoint);

  }

  public void start() {
    ValueAnimator animator1 = getValueAnimator(mRadius + mInterval);
    animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float deg = (float) animation.getAnimatedValue();
        float rad = (float) (Math.PI * deg / 180);
        Y1 = (float) (Math.sin(rad) * (mRadius + mInterval));
        X1 = (float) (Math.cos(rad) * (mRadius + mInterval));
      }
    });
    animator1.start();

    ValueAnimator animator2 = getValueAnimator(mRadius + mInterval * 2);
    animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float deg = (float) animation.getAnimatedValue();
        float rad = (float) (Math.PI * deg / 180);
        Y2 = (float) (Math.sin(rad) * (mRadius + mInterval*2));
        X2 = (float) (Math.cos(rad) * (mRadius + mInterval*2));
      }
    });
    animator2.start();

    ValueAnimator animator3 = getValueAnimator(mRadius + mInterval * 3);
    animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float deg = (float) animation.getAnimatedValue();
        float rad = (float) (Math.PI * deg / 180);
        Y3 = (float) (Math.sin(rad) * (mRadius + mInterval*3));
        X3 = (float) (Math.cos(rad) * (mRadius + mInterval*3));
        invalidate();
      }
    });
    animator3.start();
  }

  private ValueAnimator getValueAnimator(int radius) {
    ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
    animator.setRepeatMode(ValueAnimator.RESTART);
    animator.setRepeatCount(ValueAnimator.INFINITE);
    animator.setDuration(radius * 30);
    animator.setInterpolator(new LinearInterpolator());
    return animator;
  }
}
