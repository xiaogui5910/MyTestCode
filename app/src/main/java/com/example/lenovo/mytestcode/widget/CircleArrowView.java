package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lenovo.mytestcode.R;

/**
 * Created by ccg on 2017/8/3.
 */

public class CircleArrowView extends View {

  private Paint mCirclePaint;
  private Bitmap mBitmap;
  private Paint mBitmapPaint;
  private int mWidth;
  private int mHeight;
  private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
  private float[] pos;
  private float[] tan;
  private Matrix mMatrix;

  public CircleArrowView(Context context) {
    super(context);
    init(context);
  }

  public CircleArrowView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public CircleArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    pos =new float[2];
    tan =new float[2];
    mMatrix = new Matrix();

    mCirclePaint = new Paint();
    mCirclePaint.setStyle(Paint.Style.STROKE);
    mCirclePaint.setColor(Color.BLACK);
    mCirclePaint.setAntiAlias(true);

    mBitmapPaint = new Paint();
    mBitmapPaint.setAntiAlias(true);

    BitmapFactory.Options opts = new BitmapFactory.Options();
    opts.inSampleSize = 10;
    mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, opts);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
    mHeight = h;
    new Thread(){
      @Override
      public void run() {
        while (true){
          currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
          if (currentValue >= 1) {
            currentValue = 0;
          }
          postInvalidate();
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }.start();
  }

  @Override
  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);
    canvas.translate(mWidth/2,mHeight/2);
    Path mPath = new Path();
    mPath.addCircle(0,0,200, Path.Direction.CW);
    PathMeasure mPathMeasure = new PathMeasure(mPath,false);

    //第一种方式
//    mPathMeasure.getPosTan(currentValue*mPathMeasure.getLength(),pos,tan);
//    mMatrix.reset();
//    float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
//
//    mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
//    mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

    //第二种方式
    mPathMeasure.getMatrix(mPathMeasure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
    mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)

    canvas.drawPath(mPath, mCirclePaint);                                   // 绘制 Path
    canvas.drawBitmap(mBitmap, mMatrix, mCirclePaint);                     // 绘制箭头

//    canvas.translate(mWidth / 2, mHeight / 2);      // 平移坐标系
//
//    Path path = new Path();                                 // 创建 Path
//
//    path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
//
//    PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure
//
//    currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
//    if (currentValue >= 1) {
//      currentValue = 0;
//    }
//
//    measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
//
//    mMatrix.reset();                                                        // 重置Matrix
//    float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
//    Log.e(TAG, "onDraw: currentValue="+currentValue+",degrees="+degrees );
//
//    mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
//    mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合
//
//    canvas.drawPath(path, mCirclePaint);                                   // 绘制 Path
//    canvas.drawBitmap(mBitmap, mMatrix, null);                     // 绘制箭头
//
//    invalidate();

  }
}
