package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.lenovo.mytestcode.R;

/**
 * Created by ccg on 2017/7/26.
 */

public class TextImageView extends View {
  private static final int SCALE_TYPE_FITXY = 0;
  private static final int SCALE_TYPE_CENTER = 1;
  private static final String TAG = "TextImageView";

  private String mTitleText;
  private int mTitleTextColor;
  private int mTitleTextSize;
  private Bitmap mImage;
  private int mImageScaleType;
  private Paint mPaint;
  private Rect rect;
  private Rect mBound;
  private int mWidth;
  private int mHeight;

  public TextImageView(Context context) {
    this(context, null);
  }

  public TextImageView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TextImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextImageView, defStyleAttr, 0);
    int indexCount = ta.getIndexCount();
    for (int i = 0; i < indexCount; i++) {
      int attr = ta.getIndex(i);
      switch (attr) {
        case R.styleable.TextImageView_image:
          mImage = BitmapFactory.decodeResource(getResources(), ta.getResourceId(attr, 0));
          break;
        case R.styleable.TextImageView_image_scale_type:
          mImageScaleType = ta.getInt(attr, 0);
          break;
        case R.styleable.TextImageView_title_text:
          mTitleText = ta.getString(attr);
          break;
        case R.styleable.TextImageView_title_text_color:
          mTitleTextColor = ta.getColor(attr, Color.RED);
          break;
        case R.styleable.TextImageView_title_text_size:
          mTitleTextSize = ta.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
          break;
      }
    }

    ta.recycle();

    rect = new Rect();
    mPaint = new Paint();
    mBound = new Rect();
    mPaint.setTextSize(mTitleTextSize);
    mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

    if (widthMode == MeasureSpec.EXACTLY) {
      mWidth = widthSize;
    } else {
      int desireByImg = getPaddingLeft() + mImage.getWidth() + getPaddingRight();
      int desireByText = getPaddingLeft() + mBound.width() + getPaddingRight();
      int desired = Math.max(desireByImg, desireByText);
      if (widthMode == MeasureSpec.AT_MOST) {
        mWidth = Math.min(desired, widthSize);
      }
    }

    if (heightMode == MeasureSpec.EXACTLY) {
      mHeight = heightSize;
    } else {
      int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mBound.height();
      if (heightMode == MeasureSpec.AT_MOST) {
        mHeight = Math.min(desire, heightSize);
      }
    }

    setMeasuredDimension(mWidth, mHeight);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    mPaint.setColor(Color.GREEN);
    mPaint.setAntiAlias(true);
    mPaint.setStrokeWidth(4);
    mPaint.setStyle(Paint.Style.STROKE);
    canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

    rect.left = getPaddingLeft();
    rect.right = mWidth - getPaddingRight();
    rect.top = getPaddingTop();
    rect.bottom = mHeight - getPaddingBottom();

    mPaint.setColor(mTitleTextColor);
    mPaint.setStyle(Paint.Style.FILL);

    if (mBound.width() > mWidth) {
      TextPaint textPaint = new TextPaint(mPaint);
      String msg = TextUtils.ellipsize(mTitleText, textPaint, mWidth - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END).toString();
      canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
    } else {
      canvas.drawText(mTitleText, mWidth / 2 - mBound.width() / 2, mHeight - getPaddingBottom(), mPaint);
    }

    rect.bottom -= mBound.height();

    if (mImageScaleType == SCALE_TYPE_FITXY) {
      canvas.drawBitmap(mImage, null, rect, mPaint);
    } else {
      rect.left = mWidth / 2 - mImage.getWidth() / 2;
      rect.right = mWidth / 2 + mImage.getWidth() / 2;
      rect.top = (mHeight - mBound.height()) / 2 - mImage.getHeight() / 2;
      rect.bottom = (mHeight - mBound.height()) / 2 + mImage.getHeight() / 2;
      canvas.drawBitmap(mImage, null, rect, mPaint);
    }
  }

}
