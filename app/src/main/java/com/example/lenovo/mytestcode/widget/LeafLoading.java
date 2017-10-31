package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ccg on 2017/8/1.
 */

public class LeafLoading extends View {
  private static final String TAG = "LeafLoading";
  // 淡白色
  private static final int WHITE_COLOR = 0xfffde399;
  // 橙色
  private static final int ORANGE_COLOR = 0xffffa800;
  //控制绘制的进度条距离左/上/右的距离
  private static final int LEFT_MARGIN = 9;
  private static final int RIGHT_MARGIN = 25;
  private int mLeftMargin, mRightMargin;
  //叶子飘动一周所花的时间
  private static final long LEAF_FLOAT_TIME = 3000;
  //叶子旋转一周所花的时间
  private static final long LEAF_ROTATE_TIME = 2000;
  private long mLeafFloatTime = LEAF_FLOAT_TIME;
  private long mLeafRotateTime = LEAF_ROTATE_TIME;
  //用于控制叶子随机增加的时间，防止同时（抱团）出现
  private long mAddTime;
  private Resources mResources;
  private Bitmap mLeafBitmap;
  private int mLeafWidth;
  private int mLeafHeight;
  private Bitmap mOuterBitmap;
  private int mOuterWidth;
  private int mOuterHeight;
  private Paint mOrangePaint;
  private Paint mWhitePaint;
  private Paint mBitmapPaint;
  private List<Leaf> mLeafInfos;
  private LeafFactory mLeafFactory;
  private int mTotalWidth;
  private int mTotalHeight;
  private int mProgressWidth;
  private int mArcRadius;
  private Rect mOuterSrcRect;
  private Rect mOuterDstRect;
  // 当前所在的绘制的进度条的位置
  private int mCurrentProgressPosition;
  private RectF mArcRectF;
  private RectF mWhiteRectF;
  private RectF mOrangeRectF;
  // arc的右上角的x坐标，也是矩形x坐标的起始点
  private int mArcRightLocation;
  // 当前进度
  private int mProgress;
  // 总进度
  private static final int TOTAL_PROGRESS = 100;
  // 中等振幅大小
  private static final int MIDDLE_AMPLITUDE = 13;
  // 不同类型之间的振幅差距
  private static final int AMPLITUDE_DISPARITY = 5;
  // 中等振幅大小
  private int mMiddleAmplitude = MIDDLE_AMPLITUDE;
  // 振幅差
  private int mAmplitudeDisparity = AMPLITUDE_DISPARITY;


  public LeafLoading(Context context) {
    this(context, null);
  }

  public LeafLoading(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LeafLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mResources = getResources();
    mLeftMargin = DensityUtils.dp2px(context, LEFT_MARGIN);
    mRightMargin = DensityUtils.dp2px(context, RIGHT_MARGIN);
    mLeafFloatTime = LEAF_FLOAT_TIME;
    mLeafRotateTime = LEAF_ROTATE_TIME;

    intiBitmap();
    initPaint();

    mLeafFactory = new LeafFactory();
    mLeafInfos = mLeafFactory.generateLeafs();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mTotalWidth = w;
    mTotalHeight = h;

    mProgressWidth = mTotalWidth - mLeftMargin - mRightMargin;
    mArcRadius = (mTotalHeight - 2 * mLeftMargin) / 2;

    mOuterSrcRect = new Rect(0, 0, mOuterWidth, mOuterHeight);
    mOuterDstRect = new Rect(0, 0, mTotalWidth, mTotalHeight);

    mArcRectF = new RectF(mLeftMargin, mLeftMargin, mLeftMargin + 2 * mArcRadius, mTotalHeight - mLeftMargin);
    mWhiteRectF = new RectF(mLeftMargin + mCurrentProgressPosition, mLeftMargin, mTotalWidth - mRightMargin, mTotalHeight - mLeftMargin);
    mOrangeRectF = new RectF(mLeftMargin + mArcRadius, mLeftMargin, mCurrentProgressPosition, mTotalHeight - mLeftMargin);

    mArcRightLocation = mLeftMargin + mArcRadius;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawProgressAndLeafs(canvas);
  }

  private void drawProgressAndLeafs(Canvas canvas) {
    if (mProgress >= TOTAL_PROGRESS) {
      mProgress = 0;
    }
    //mProgressWidth为进度条宽度，根据当前进度算出进度条位置
    mCurrentProgressPosition = mProgressWidth * mProgress / TOTAL_PROGRESS;
    //还在半圆内
    if (mCurrentProgressPosition < mArcRadius) {
      Log.d(TAG, "drawProgressAndLeafs: mProgress = " + mProgress + "---mCurrentProgressPosition = "
              + mCurrentProgressPosition
              + "--mArcProgressWidth" + mArcRadius);

      //绘制白色ARC
      canvas.drawArc(mArcRectF, 90, 180, false, mWhitePaint);
      //绘制白色矩形
      mWhiteRectF.left = mArcRightLocation;
      canvas.drawRect(mWhiteRectF, mWhitePaint);

      //绘制叶子
      drawLeafs(canvas);
      int angle = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressPosition) / (float) mArcRadius));
      int startAngle = 180 - angle;
      int sweepAngle = 2 * angle;
      Log.d(TAG, "startAngle = " + startAngle);
      canvas.drawArc(mArcRectF, startAngle, sweepAngle, false, mOrangePaint);
    } else {
      Log.d(TAG, "mProgress = " + mProgress + "---transfer-----mCurrentProgressPosition = "
              + mCurrentProgressPosition
              + "--mArcProgressWidth" + mArcRadius);
      mWhiteRectF.left = mCurrentProgressPosition;
      canvas.drawRect(mWhiteRectF, mWhitePaint);
      drawLeafs(canvas);
      canvas.drawArc(mArcRectF, 90, 180, false, mOrangePaint);
      mOrangeRectF.left = mArcRightLocation;
      mOrangeRectF.right = mCurrentProgressPosition;
      canvas.drawRect(mWhiteRectF, mOrangePaint);
    }

  }

  private void drawLeafs(Canvas canvas) {
    mLeafFloatTime = mLeafRotateTime <= 0 ? LEAF_FLOAT_TIME : mLeafRotateTime;
    long currentTime = System.currentTimeMillis();
    for (int i = 0; i < mLeafInfos.size(); i++) {
      Leaf leaf = mLeafInfos.get(i);
      if (currentTime > leaf.startTime && leaf.startTime != 0) {
        //绘制叶子---根据叶子的类型和当前时间得出叶子的x,y
        getLeafLocation(leaf, currentTime);
        canvas.save();
        Matrix matrix = new Matrix();
        float transX = mLeftMargin + leaf.x;
        float transY = mLeftMargin + leaf.y;
        Log.d(TAG, "left.x = " + leaf.x + "--leaf.y=" + leaf.y);
        matrix.postTranslate(transX, transY);

        float rotateFraction = ((currentTime - leaf.startTime) % mLeafFloatTime) / (float) mLeafFloatTime;
        int angle = (int) (rotateFraction * 360);
        int rotate = leaf.rotataDirection == 0 ? angle + leaf.rotateAngle : -angle + leaf.rotateAngle;
        matrix.postRotate(rotate, transX + mLeafWidth / 2, transY + mLeftMargin / 2);
        canvas.drawBitmap(mLeafBitmap, matrix, mBitmapPaint);
        canvas.restore();
      } else {
        continue;
      }
    }
  }

  private void getLeafLocation(Leaf leaf, long currentTime) {
    long intervalTime = currentTime - leaf.startTime;
    mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
    if (intervalTime < 0) {
      return;
    } else if (intervalTime > mLeafFloatTime) {
      leaf.startTime = System.currentTimeMillis() + new Random().nextInt((int) mLeafFloatTime);
    }

    float fraction = (float) intervalTime / mLeafFloatTime;
    leaf.x = (int) (mProgressWidth - mProgressWidth * fraction);
    leaf.y = getLocationY(leaf);

  }

  private int getLocationY(Leaf leaf) {
    //y=A(wx+Q)+h
    float w = (float) ((float) 2 * Math.PI / mProgressWidth);
    float a = mMiddleAmplitude;
    switch (leaf.type) {
      case LITTLE:
        // 小振幅 ＝ 中等振幅 － 振幅差
        a = mMiddleAmplitude - mAmplitudeDisparity;
        break;
      case MIDDLE:
        a = mMiddleAmplitude;
        break;
      case BIG:
        // 小振幅 ＝ 中等振幅 + 振幅差
        a = mMiddleAmplitude + mAmplitudeDisparity;
        break;
      default:
        break;
    }
    Log.d(TAG, "---a = " + a + "---w = " + w + "--leaf.x = " + leaf.x);

    return (int) (a * Math.sin(w * leaf.x) + mArcRadius * 2 / 3);
  }

  private void intiBitmap() {
    mLeafBitmap = BitmapFactory.decodeResource(mResources, R.drawable.leaf);
    mLeafWidth = mLeafBitmap.getWidth();
    mLeafHeight = mLeafBitmap.getHeight();

    mOuterBitmap = BitmapFactory.decodeResource(mResources, R.drawable.leaf_kuang);
    mOuterWidth = mLeafBitmap.getWidth();
    mOuterHeight = mLeafBitmap.getHeight();
  }

  private void initPaint() {
    mBitmapPaint = new Paint();
    mBitmapPaint.setAntiAlias(true);
    mBitmapPaint.setDither(true);
    mBitmapPaint.setFilterBitmap(true);

    mWhitePaint = new Paint();
    mWhitePaint.setAntiAlias(true);
    mWhitePaint.setColor(WHITE_COLOR);

    mOrangePaint = new Paint();
    mWhitePaint.setAntiAlias(true);
    mWhitePaint.setColor(ORANGE_COLOR);
  }

  private enum StartType {
    LITTLE, MIDDLE, BIG
  }

  class Leaf {
    private int x;
    private int y;
    //控制叶子的幅度
    StartType type;
    //旋转角度
    private int rotateAngle;
    //旋转方向，0顺时针，1逆时针
    private int rotataDirection;
    //开始时间
    long startTime;
  }

  /**
   * 生产叶子工厂类
   */
  class LeafFactory {
    private static final int MAX_LEAF = 8;

    public List<Leaf> generateLeafs() {
      return generateLeafs(MAX_LEAF);
    }

    private List<Leaf> generateLeafs(int leafSize) {
      List<Leaf> leafs = new ArrayList<>();
      for (int i = 0; i < leafSize; i++) {
        leafs.add(generateLeaf());
      }
      return leafs;
    }

    public Leaf generateLeaf() {
      Leaf leaf = new Leaf();
      Random r = new Random();
      //随机叶子幅度大小
      int randomType = r.nextInt(3);
      switch (randomType) {
        case 0:
          leaf.type = StartType.LITTLE;
          break;
        case 1:
          leaf.type = StartType.MIDDLE;
          break;
        case 2:
          leaf.type = StartType.BIG;
          break;
      }
      //随机叶子方向
      leaf.rotataDirection = r.nextInt(2);
      //随机起始旋转角度
      leaf.rotateAngle = r.nextInt(360);
      //随机开始时间，错乱效果
      mLeafFloatTime = mLeafRotateTime <= 0 ? LEAF_FLOAT_TIME : mLeafRotateTime;
      mAddTime += r.nextInt((int) (mLeafFloatTime * 2));
      leaf.startTime = System.currentTimeMillis() + mAddTime;
      return leaf;
    }
  }


  /**
   * 设置中等振幅
   *
   * @param amplitude
   */
  public void setMiddleAmplitude(int amplitude) {
    this.mMiddleAmplitude = amplitude;
  }

  /**
   * 设置振幅差
   *
   * @param disparity
   */
  public void setMplitudeDisparity(int disparity) {
    this.mAmplitudeDisparity = disparity;
  }

  /**
   * 获取中等振幅
   *
   */
  public int getMiddleAmplitude() {
    return mMiddleAmplitude;
  }

  /**
   * 获取振幅差
   *
   */
  public int getMplitudeDisparity() {
    return mAmplitudeDisparity;
  }

  /**
   * 设置进度
   *
   * @param progress
   */
  public void setProgress(int progress) {
    this.mProgress = progress;
    postInvalidate();
  }

  /**
   * 设置叶子飘完一个周期所花的时间
   *
   * @param time
   */
  public void setLeafFloatTime(long time) {
    this.mLeafFloatTime = time;
  }

  /**
   * 设置叶子旋转一周所花的时间
   *
   * @param time
   */
  public void setLeafRotateTime(long time) {
    this.mLeafRotateTime = time;
  }

  /**
   * 获取叶子飘完一个周期所花的时间
   */
  public long getLeafFloatTime() {
    mLeafFloatTime = mLeafFloatTime == 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
    return mLeafFloatTime;
  }

  /**
   * 获取叶子旋转一周所花的时间
   */
  public long getLeafRotateTime() {
    mLeafRotateTime = mLeafRotateTime == 0 ? LEAF_ROTATE_TIME : mLeafRotateTime;
    return mLeafRotateTime;
  }
}
