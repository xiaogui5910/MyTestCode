package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ccg on 2017/8/3.
 */

public class RadarView extends View {
  private static final String TAG = "RadarView";

  private Paint mMainPaint;
  private Paint mValuePaint;
  private int mWidth;
  private int mHeight;

  private int count = 6;
  private float angle = (float) (2 * Math.PI / count);
  private int maxValue = 100;
  private int value;
  private String[] titles = {"A", "B", "C", "D", "E", "F"};
  private int[] values = {60, 83, 72, 30, 96, 65};
  private Path mPath;
  private int centerX;
  private int centerY;
  private float radius;
  private Paint mTextPaint;

  public RadarView(Context context) {
    this(context, null);
  }

  public RadarView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mMainPaint = new Paint();
    mMainPaint.setAntiAlias(true);
    mMainPaint.setStyle(Paint.Style.STROKE);
    mMainPaint.setColor(Color.WHITE);

    mValuePaint = new Paint();
    mValuePaint.setColor(Color.RED);
    mValuePaint.setAntiAlias(true);
    mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

    mTextPaint = new Paint();
    mTextPaint.setColor(Color.WHITE);
    mTextPaint.setAntiAlias(true);
    mTextPaint.setStyle(Paint.Style.FILL);
    mTextPaint.setTextSize(50);

    mPath = new Path();

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {

    centerX = w / 2;
    centerY = h / 2;
    int min = Math.min(w, h);
    radius = min / 2 * 0.8f;
    Log.e(TAG, "onSizeChanged: radius=" + radius);
    postInvalidate();
    super.onSizeChanged(w, h, oldw, oldh);
  }

  @Override
  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);

    //绘制多边形
    drawPolygon(canvas);
    //绘制线条
    drawLines(canvas);
    //绘制文本
    drawTexts(canvas);
    //绘制覆盖区域
    drawRegion(canvas);

  }

  private void drawPolygon(Canvas canvas) {
//    Path path = new Path();
//    for (int i = 0; i < count; i++) {
//      if (i==0){
//        path.moveTo(centerX+radius,centerY);
//      }else {
//        float x = (float) (centerX+radius*Math.cos(angle*i));
//        float y = (float) (centerY+ radius*Math.sin(angle*i));
//        path.lineTo(x,y);
//      }
//    }
//    path.close();
//    canvas.drawPath(path,mMainPaint);
//    float r = radius / (count/2); //多边形间距
//    float fraction = 1-r/radius;
//    for (int i = 0; i < count - 1; i++) {
//      canvas.scale(fraction,fraction,centerX,centerY);
//      canvas.drawPath(path,mMainPaint);
//    }

    Path path = new Path();
    float r = radius / (count - 1); //多边形间距
    for (int i = 1; i < count; i++) { //从1开始，中心点不用绘制
      float currR = r * i;
      path.reset();
      for (int j = 0; j < count; j++) {
        if (j == 0) {
          path.moveTo(centerX + currR, centerY);
        } else {
          float x = (float) (centerX + currR * Math.cos(angle * j));
          float y = (float) (centerY + currR * Math.sin(angle * j));
          path.lineTo(x, y);
        }
      }
      //闭合路径
      path.close();
      //开始绘制
      canvas.drawPath(path, mMainPaint);
    }
  }

  private void drawLines(Canvas canvas) {
    Path path = new Path();
    for (int i = 0; i < count; i++) {
      path.reset();
      path.moveTo(centerX, centerY);
      float x = (float) (centerX + radius * Math.cos(angle * i));
      float y = (float) (centerY + radius * Math.sin(angle * i));
      path.lineTo(x, y);
      canvas.drawPath(path, mMainPaint);
    }
  }

  private void drawTexts(Canvas canvas) {
    Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
    float fontHeight = fontMetrics.descent - fontMetrics.ascent;
    for (int i = 0; i < count; i++) {
      float currAngle = angle * i;
      float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(currAngle));
      float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(currAngle));
      if (currAngle >= 0 && currAngle <= Math.PI / 2) {
        canvas.drawText(titles[i], x, y, mTextPaint);
      } else if (currAngle >= Math.PI * 3 / 2 && currAngle <= Math.PI * 2) {
        canvas.drawText(titles[i], x, y, mTextPaint);
      } else if (currAngle > Math.PI / 2 && currAngle < Math.PI * 3 / 2) {
        float dis = mTextPaint.measureText(titles[i]);
        canvas.drawText(titles[i], x - dis, y, mTextPaint);
      }
    }
  }

  //  private void drawRegion(Canvas canvas) {
//    Path path = new Path();
//    mValuePaint.setAlpha(255);
//    for (int i = 0; i < count; i++) {
//      double percentage = values[i]/maxValue;
//      float x = (float) (centerX+radius*Math.cos(angle*i)*percentage);
//      float y = (float) (centerY+radius*Math.sin(angle*i)*percentage);
//      Log.e(TAG, "drawRegion: x="+x+"-------y="+y );
//      if (i==0){
//        path.moveTo(x,centerY);
//      }else {
//        path.lineTo(x,y);
//      }
//      canvas.drawCircle(x,y,10,mValuePaint);
//    }
//    mValuePaint.setStyle(Paint.Style.STROKE);
//    canvas.drawPath(path,mValuePaint);
//    mValuePaint.setAlpha(127);
//    mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
//    canvas.drawPath(path,mValuePaint);
//  }
  private void drawRegion(Canvas canvas) {
    Path path = new Path();
    mValuePaint.setAlpha(255);
    for (int i = 0; i < count; i++) {
      double percent = values[i]*1.0f / maxValue;
      float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
      float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
//      Log.e(TAG, "drawRegion: x=" + x + "-------y=" + y);
      if (i == 0) {
        path.moveTo(x, centerY);
      } else {
        path.lineTo(x, y);
      }
      //绘制小圆点
      canvas.drawCircle(x, y, 10, mValuePaint);
    }
    mValuePaint.setStyle(Paint.Style.STROKE);
    canvas.drawPath(path, mValuePaint);
    mValuePaint.setAlpha(127);
    //绘制填充区域
    mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    canvas.drawPath(path, mValuePaint);
  }


}


//public class RadarView extends View {
//  private static final String TAG = "RadarView";
//  private int count = 6;                //数据个数
//  private float angle = (float) (Math.PI*2/count);
//  private float radius;                   //网格最大半径
//  private int centerX;                  //中心X
//  private int centerY;                  //中心Y
//  private String[] titles = {"a","b","c","d","e","f"};
//  private double[] data = {100,60,60,60,100,50,10,20}; //各维度分值
//  private float maxValue = 100;             //数据最大值
//  private Paint mainPaint;                //雷达区画笔
//  private Paint valuePaint;               //数据区画笔
//  private Paint textPaint;                //文本画笔
//
//  public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
//    super(context, attrs, defStyleAttr);
//    init();
//  }
//
//  public RadarView(Context context, AttributeSet attrs) {
//    super(context, attrs);
//    init();
//  }
//
//  public RadarView(Context context) {
//    super(context);
//    init();
//  }
//
//
//  //初始化
//  private void init() {
//    count = Math.min(data.length,titles.length);
//
//    mainPaint = new Paint();
//    mainPaint.setAntiAlias(true);
//    mainPaint.setColor(Color.GRAY);
//    mainPaint.setStyle(Paint.Style.STROKE);
//
//    valuePaint = new Paint();
//    valuePaint.setAntiAlias(true);
//    valuePaint.setColor(Color.BLUE);
//    valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
//
//    textPaint = new Paint();
//    textPaint.setTextSize(20);
//    textPaint.setStyle(Paint.Style.FILL);
//    textPaint.setColor(Color.WHITE);
//  }
//
//  @Override
//  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//    radius = Math.min(h, w)/2*0.9f;
//    centerX = w/2;
//    centerY = h/2;
//    postInvalidate();
//    super.onSizeChanged(w, h, oldw, oldh);
//  }
//
//
//
//  @Override
//  protected void onDraw(Canvas canvas) {
//    drawPolygon(canvas);
//    drawLines(canvas);
//    drawText(canvas);
//    drawRegion(canvas);
//  }
//
//  /**
//   * 绘制正多边形
//   */
//  private void drawPolygon(Canvas canvas){
//    Path path = new Path();
//    float r = radius/(count-1);
//    for(int i=1;i<count;i++){
//      float curR = r*i;
//      path.reset();
//      for(int j=0;j<count;j++){
//        if(j==0){
//          path.moveTo(centerX+curR,centerY);
//        }else{
//          float x = (float) (centerX+curR*Math.cos(angle*j));
//          float y = (float) (centerY+curR*Math.sin(angle*j));
//          path.lineTo(x,y);
//        }
//      }
//      path.close();
//      canvas.drawPath(path, mainPaint);
//    }
//  }
//
//  /**
//   * 绘制直线
//   */
//  private void drawLines(Canvas canvas){
//    Path path = new Path();
//    for(int i=0;i<count;i++){
//      path.reset();
//      path.moveTo(centerX, centerY);
//      float x = (float) (centerX+radius*Math.cos(angle*i));
//      float y = (float) (centerY+radius*Math.sin(angle*i));
//      path.lineTo(x, y);
//      canvas.drawPath(path, mainPaint);
//    }
//  }
//
//  /**
//   * 绘制文字
//   * @param canvas
//   */
//  private void drawText(Canvas canvas){
//    Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
//    float fontHeight = fontMetrics.descent - fontMetrics.ascent;
//    for(int i=0;i<count;i++){
//      float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
//      float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
//      if(angle*i>=0&&angle*i<=Math.PI/2){//第4象限
//        canvas.drawText(titles[i], x,y,textPaint);
//      }else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
//        canvas.drawText(titles[i], x,y,textPaint);
//      }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
//        float dis = textPaint.measureText(titles[i]);//文本长度
//        canvas.drawText(titles[i], x-dis,y,textPaint);
//      }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
//        float dis = textPaint.measureText(titles[i]);//文本长度
//        canvas.drawText(titles[i], x-dis,y,textPaint);
//      }
//    }
//  }
//
//  /**
//   * 绘制区域
//   * @param canvas
//   */
//  private void drawRegion(Canvas canvas){
//    Path path = new Path();
//    valuePaint.setAlpha(255);
//    for(int i=0;i<count;i++){
//      double percent = data[i]/maxValue;
//      float x = (float) (centerX+radius*Math.cos(angle*i)*percent);
//      float y = (float) (centerY+radius*Math.sin(angle*i)*percent);
//      Log.e(TAG, "drawRegion: x=" + x + "-------y=" + y);
//      if(i==0){
//        path.moveTo(x, centerY);
//      }else{
//        path.lineTo(x,y);
//      }
//      //绘制小圆点
//      canvas.drawCircle(x,y,10,valuePaint);
//    }
//    valuePaint.setStyle(Paint.Style.STROKE);
//    canvas.drawPath(path, valuePaint);
//    valuePaint.setAlpha(127);
//    //绘制填充区域
//    valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
//    canvas.drawPath(path, valuePaint);
//  }
//
//  //设置标题
//  public void setTitles(String[] titles) {
//    this.titles = titles;
//  }
//
//  //设置数值
//  public void setData(double[] data) {
//    this.data = data;
//  }
//
//
//  public float getMaxValue() {
//    return maxValue;
//  }
//
//  //设置最大数值
//  public void setMaxValue(float maxValue) {
//    this.maxValue = maxValue;
//  }
//
//  //设置蜘蛛网颜色
//  public void setMainPaintColor(int color){
//    mainPaint.setColor(color);
//  }
//
//  //设置标题颜色
//  public void setTextPaintColor(int color){
//    textPaint.setColor(color);
//  }
//
//  //设置覆盖局域颜色
//  public void setValuePaintColor(int color){
//    valuePaint.setColor(color);
//  }
//}
