package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.lenovo.mytestcode.R;

import java.util.Random;

/**
 * Created by ccg on 2017/7/26.
 */

public class RdTextView extends View {
  private int mTextColor;
  private int mTextSize;
  private String mText;

  private Paint mPaint;
  private Rect mBound;

  public RdTextView(Context context) {
    this(context, null);
  }

  public RdTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RdTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RdTextView, defStyleAttr, 0);
    int count = ta.getIndexCount();
    for (int i = 0; i < count; i++) {
      int attr = ta.getIndex(i);
      switch (attr) {
        case R.styleable.RdTextView_text:
          mText = ta.getString(attr);
          break;
        case R.styleable.RdTextView_text_color:
          mTextColor = ta.getColor(attr, Color.RED);
          break;
        case R.styleable.RdTextView_text_size:
          mTextSize = ta.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                  TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
          break;
      }
    }
    ta.recycle();

    mPaint = new Paint();
//    mPaint.setColor(mTextColor);
    mPaint.setTextSize(mTextSize);

    mBound = new Rect();

    mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    init();
  }

  private void init() {
    this.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        mText = getRandomText();
        postInvalidate();
      }
    });
  }

  private String getRandomText() {
    Random r = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 4; i++) {
      int num = r.nextInt(10);
      sb.append(num+"");
    }
    return sb.toString();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

    int width;
    int height;

    if (widthMode == MeasureSpec.EXACTLY) {
      width = widthSize;
    } else {
      mPaint.setTextSize(mTextSize);
      mPaint.getTextBounds(mText, 0, mText.length(), mBound);
      int desired = getPaddingLeft() + mBound.width() + getPaddingRight();
      width = desired;
    }

    if (heightMode == MeasureSpec.EXACTLY) {
      height = heightSize;
    } else {
      mPaint.setTextSize(mTextSize);
      mPaint.getTextBounds(mText, 0, mText.length(), mBound);
      int desired = getPaddingTop() + mBound.height() + getPaddingBottom();
      height = desired;
    }

    setMeasuredDimension(width, height);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPaint.setColor(Color.YELLOW);
    canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

    mPaint.setColor(mTextColor);
    canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
  }

}
