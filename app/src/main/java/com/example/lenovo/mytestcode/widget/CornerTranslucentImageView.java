package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.lenovo.mytestcode.R;

/**
 * Created by ccg on 2018/5/16.
 */

public class CornerTranslucentImageView extends ImageView {
  private final int DEFAULT_RADIUS = 10;

  private Paint paint = new Paint();
  private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
  private Bitmap maskBitmap = null;
  private RectF rectF = new RectF();

  private int oldWidth;
  private int oldHeight;
  /**
   * 圆角的弧度
   */
  private float radius;
  private Paint colorPaint;
  private RectF colorRectF;

  public CornerTranslucentImageView(Context context) {
    this(context, null);
  }

  public CornerTranslucentImageView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public CornerTranslucentImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CornerTranslucentImageView, defStyleAttr, 0);
    radius = a.getDimensionPixelSize(R.styleable.CornerTranslucentImageView_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_RADIUS, getResources().getDisplayMetrics()));
    a.recycle();

    paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    paint.setAntiAlias(true);

    colorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    if (getScaleType() != ScaleType.CENTER_CROP) {
//      setScaleType(ScaleType.CENTER_CROP);
//    }
  }


  private void create_mask_bitmap(int w, int h) {
    if (null != maskBitmap && !maskBitmap.isRecycled()) {
      maskBitmap.recycle();
      maskBitmap = null;
    }

    maskBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    Canvas mCanvas = new Canvas(maskBitmap);
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Color.TRANSPARENT);
    mCanvas.drawRoundRect(new RectF(0, 0, w, h), radius, radius, paint);

    LinearGradient linearGradient = new LinearGradient(w/2, 0, w/2, h, Color.TRANSPARENT, Color.RED, Shader.TileMode.CLAMP);
    colorPaint.setShader(linearGradient);
    colorRectF = new RectF(0, 0, w, h);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());

    if (oldWidth != getMeasuredWidth() || oldHeight != getMeasuredHeight()) {
      oldWidth = getMeasuredWidth();
      oldHeight = getMeasuredHeight();

      if (oldWidth > 0 && oldHeight > 0) {
        create_mask_bitmap(oldWidth, oldHeight);
      }
    }

  }

  @Override
  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);

    Drawable mDrawable = getDrawable();
    if (mDrawable == null) {
      return;
    }

    if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
      return;     // nothing to draw (empty bounds)
    }

    if (null == maskBitmap) {
      return;
    }

    int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
            Canvas.MATRIX_SAVE_FLAG |
                    Canvas.CLIP_SAVE_FLAG |
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                    Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                    Canvas.CLIP_TO_LAYER_SAVE_FLAG);

    canvas.save();
    Matrix mDrawMatrix = getImageMatrix();
    if (mDrawMatrix != null) {
      canvas.concat(mDrawMatrix);
    }
    mDrawable.draw(canvas);
    canvas.restore();

    paint.setXfermode(xfermode);
    canvas.drawBitmap(maskBitmap, null, rectF, paint);
    paint.setXfermode(null);

    canvas.drawRoundRect(colorRectF,radius,radius, colorPaint);

    canvas.restoreToCount(saveCount);
  }
}
