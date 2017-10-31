package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ccg on 2017/8/11.
 */

//public class SurfaceViewL extends SurfaceView implements SurfaceHolder.Callback ,Runnable{
//  private final static String TAG ="SurfaceViewL";
//
//  private SurfaceHolder mSurfaceHolder;
//  private boolean isDrawing;
//  private Paint mPaint;
//  private Path mPath;
//  private Canvas mCanvas;
//  private float mLastX;
//  private float mLastY;
//
//  public SurfaceViewL(Context context) {
//    this(context,null);
//  }
//
//  public SurfaceViewL(Context context, AttributeSet attrs) {
//    this(context, attrs,0);
//  }
//
//  public SurfaceViewL(Context context, AttributeSet attrs, int defStyleAttr) {
//    super(context, attrs, defStyleAttr);
//    init();
//  }
//
//  private void init() {
//    mSurfaceHolder = getHolder();
//    mSurfaceHolder.addCallback(this);
//
//    setFocusable(true);
//    setFocusableInTouchMode(true);
//    this.setKeepScreenOn(true);
//
//    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
//    mPaint.setStyle(Paint.Style.STROKE);
//    mPaint.setStrokeWidth(10f);
//    mPaint.setColor(Color.parseColor("#FF4081"));
//    mPaint.setStrokeJoin(Paint.Join.ROUND);
//    mPaint.setStrokeCap(Paint.Cap.ROUND);
//    mPath = new Path();
//  }
//
//  @Override
//  public void surfaceCreated(SurfaceHolder holder) {
//    isDrawing = true;
//    new Thread(this).start();
//    Log.e(TAG, "surfaceCreated: " );
//  }
//
//  @Override
//  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//  }
//
//  @Override
//  public void surfaceDestroyed(SurfaceHolder holder) {
//    isDrawing = false;
//  }
//
//  private void drawing() {
//    try {
//      mCanvas = mSurfaceHolder.lockCanvas();
//      mCanvas.drawColor(Color.WHITE);
//      mCanvas.drawPath(mPath,mPaint);
//    }finally {
//      if (mCanvas!=null){
//        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
//      }
//    }
//  }
//
//  @Override
//  public void run() {
//    while (isDrawing){
//      drawing();
//    }
//  }
//
//  @Override
//  public boolean onTouchEvent(MotionEvent event) {
//    float x = event.getX();
//    float y =event.getY();
//    switch (event.getAction()){
//      case MotionEvent.ACTION_DOWN:
//        mLastX =x;
//        mLastY =y;
//        mPath.moveTo(mLastX,mLastY);
//        break;
//      case MotionEvent.ACTION_MOVE:
//        float dx = Math.abs(x-mLastX);
//        float dy = Math.abs(y-mLastY);
//        if (dx>=3 ||dy>=3){
//          mPath.quadTo(mLastX,mLastY,(mLastX+x)/2,(mLastY+y)/2);
//        }
//        mLastX =x;
//        mLastY =y;
//        break;
//      case MotionEvent.ACTION_UP:
//        break;
//    }
//    return true;
//  }
//  /**
//   * 测量
//   */
//  @Override
//  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
//    int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
//    int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
//    int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);
//
//    if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST) {
//      setMeasuredDimension(300, 300);
//    } else if (wSpecMode == MeasureSpec.AT_MOST) {
//      setMeasuredDimension(300, hSpecSize);
//    } else if (hSpecMode == MeasureSpec.AT_MOST) {
//      setMeasuredDimension(wSpecSize, 300);
//    }
//  }
//}

public class SurfaceViewL extends SurfaceView implements SurfaceHolder.Callback, Runnable {
  // SurfaceHolder
  private SurfaceHolder mSurfaceHolder;
  // 画布
  private Canvas mCanvas;
  // 子线程标志位
  private boolean isDrawing;
  // 画笔
  Paint mPaint;
  // 路径
  Path mPath;
  private float mLastX, mLastY;//上次的坐标

  public SurfaceViewL(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  /**
   * 初始化
   */
  private void init() {
    //初始化 SurfaceHolder mSurfaceHolder
    mSurfaceHolder = getHolder();
    mSurfaceHolder.addCallback(this);

    setFocusable(true);
    setFocusableInTouchMode(true);
    this.setKeepScreenOn(true);
    //画笔
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    mPaint.setStrokeWidth(10f);
    mPaint.setColor(Color.parseColor("#FF4081"));
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeJoin(Paint.Join.ROUND);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    //路径
    mPath = new Path();
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {//创建
    isDrawing = true;
    Log.e("surfaceCreated", "--" + isDrawing);
    //绘制线程
    new Thread(this).start();
  }


  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {//改变

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {//销毁
    isDrawing = false;
    Log.e("surfaceDestroyed", "--" + isDrawing);
  }

  /**
   * 绘制
   */
  private void drawing() {
    try {
      mCanvas = mSurfaceHolder.lockCanvas();
      if (mCanvas!=null){
        mCanvas.drawColor(Color.WHITE);
        mCanvas.drawPath(mPath, mPaint);
      }
    } finally {
      if (mCanvas != null) {
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
      }
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:

        mLastX = x;
        mLastY = y;
        mPath.moveTo(mLastX, mLastY);
        break;
      case MotionEvent.ACTION_MOVE:
        float dx = Math.abs(x - mLastX);
        float dy = Math.abs(y - mLastY);
        if (dx >= 3 || dy >= 3) {
          mPath.quadTo(mLastX, mLastY, (mLastX + x) / 2, (mLastY + y) / 2);
        }
        mLastX = x;
        mLastY = y;
        break;
      case MotionEvent.ACTION_UP:
        break;
    }
    return true;
  }

  /**
   * 测量
   */
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
    int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
    int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

    if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST) {
      setMeasuredDimension(300, 300);
    } else if (wSpecMode == MeasureSpec.AT_MOST) {
      setMeasuredDimension(300, hSpecSize);
    } else if (hSpecMode == MeasureSpec.AT_MOST) {
      setMeasuredDimension(wSpecSize, 300);
    }
  }

  @Override
  public void run() {
    Log.e("drawing", "--" + 111111);
    long start = System.currentTimeMillis();
    while (isDrawing) {
      drawing();
    }
    long end = System.currentTimeMillis();
    if (end - start < 100) {
      try {
        Log.e("drawing", "--" + 22222);
        Thread.sleep(100 - (end - start));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

