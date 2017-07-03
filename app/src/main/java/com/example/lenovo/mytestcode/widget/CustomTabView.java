package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 2017/3/14.
 */

public class CustomTabView extends View {
  public CustomTabView(Context context) {
    super(context);
  }

  public CustomTabView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CustomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }
}
