package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lenovo.mytestcode.bean.PieData;

import java.util.List;

import static com.makeramen.roundedimageview.RoundedImageView.TAG;

/**
 * 饼状图
 * Created by ccg on 2017/7/31.
 */

public class PieView extends View {
  // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
  private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
          0xFFE6B800, 0xFF7CFC00};
  private int mWidth;
  private int mHeight;
  private List<PieData> dataList;
  private float startAngle;
  private Paint mPaint;

  public PieView(Context context) {
    this(context, null);
  }

  public PieView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setAntiAlias(true);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
    mHeight = h;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (dataList == null) {
      return;
    }

    float currentStartAngle = startAngle;
    canvas.translate(mWidth / 2, mHeight / 2);
    int r = (int) (Math.min(mWidth / 2, mHeight / 2) * 0.8);
    RectF rect = new RectF(-r, -r, r, r);
    for (int i = 0; i < dataList.size(); i++) {
      PieData pieData = dataList.get(i);
      mPaint.setColor(pieData.getColor());
      canvas.drawArc(rect, currentStartAngle, pieData.getAngle(), true, mPaint);
      currentStartAngle += pieData.getAngle();
    }
  }

  public void setStartAngle(float startAngle) {
    this.startAngle = startAngle;
    invalidate();
  }

  public void setDataList(List<PieData> dataList) {
    this.dataList = dataList;
    initData(dataList);
    invalidate();
  }

  private void initData(List<PieData> dataList) {
    if (dataList == null || dataList.size() == 0) {
      return;
    }

    int sumValue = 0;
    for (int i = 0; i < dataList.size(); i++) {
      PieData pieData = dataList.get(i);
      sumValue += pieData.getValue();
      int j = i % mColors.length;
      pieData.setColor(mColors[j]);
    }

    int sumAngle = 0;
    for (int i = 0; i < dataList.size(); i++) {
      PieData pieData = dataList.get(i);
      float value = pieData.getValue();
      float percentage = value / sumValue;
      float angle = 360 * percentage;

      pieData.setAngle(angle);
      sumAngle += angle;
      Log.e(TAG, "initData:["+i+"]angle= "+pieData.getAngle());
      Log.e(TAG, "initData: sumAngle="+sumAngle );
    }
  }
}
