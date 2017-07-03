package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dingmouren.paletteimageview.CustomImageView;

/**
 * Created by dingmouren on 2017/4/25.
 */

public class PaletteImageView extends RelativeLayout {

  private static final String TAG = com.dingmouren.paletteimageview.PaletteImageView.class.getName();
  private static final String DEFAULT_SHADOW_COLOR = "#8D8D8D";
  private int mCornerRadius = 0;
  private com.dingmouren.paletteimageview.CustomImageView mCustomImageView;
  private int imgId;
  private Paint mPaintShadow;
  private int mShadowRadiusSize;
  private final int MAX_SHADOW_RADIUS_SIZE = 40;
  private int mShadowDepth;
  private final int MAX_SHADOW_DEPTH = 28;
  private int mShadowRGB;
  private Bitmap mBitmap;
  private Bitmap mBitmapTemp;
  private RectF mRectF;
  private Palette.Swatch mSwatch;

  public PaletteImageView(Context context) {
    this(context, null);
  }

  public PaletteImageView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PaletteImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }


  private void init(Context context, AttributeSet attrs) {
    mPaintShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaintShadow.setColor(Color.WHITE);
    /**
     Paint.Style.STROKE 只绘制图形轮廓（描边）
     Paint.Style.FILL 只绘制图形内容
     Paint.Style.FILL_AND_STROKE 既绘制轮廓也绘制内容
     */
    mPaintShadow.setStyle(Paint.Style.FILL);
    //关闭硬件加速
    setLayerType(LAYER_TYPE_SOFTWARE, null);
    setPadding(80, 40, 80, 120);
    setBackgroundColor(getResources().getColor(android.R.color.transparent));
    setGravity(Gravity.CENTER);

    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, com.dingmouren.paletteimageview.R.styleable.PaletteImageView);
      imgId = a.getResourceId(com.dingmouren.paletteimageview.R.styleable.PaletteImageView_paletteSrc, -1);
      mCornerRadius = a.getDimensionPixelSize(com.dingmouren.paletteimageview.R.styleable.PaletteImageView_cornerRadis, mCornerRadius);
    } else {
      float density = context.getResources().getDisplayMetrics().density;
      mCornerRadius = (int) (mCornerRadius * density);
      imgId = -1;
    }
    mCustomImageView = new com.dingmouren.paletteimageview.CustomImageView(context);
    mCustomImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    if (imgId == -1) {
      mCustomImageView.setImageResource(android.R.color.transparent);
    } else {
      mCustomImageView.setImageResource(imgId);
    }
    addView(mCustomImageView);
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    View view = getChildAt(0);
    if (null == view) {
      return;
    }
    canvas.save();

    mShadowRadiusSize = view.getHeight() / 12 > MAX_SHADOW_RADIUS_SIZE ? MAX_SHADOW_RADIUS_SIZE : view.getHeight() / 12;
    mShadowDepth = view.getHeight() / 16 > MAX_SHADOW_DEPTH ? MAX_SHADOW_DEPTH : view.getHeight() / 16;


    if (((ImageView) view).getDrawable() instanceof ClipDrawable) {
      mShadowRGB = Color.parseColor(DEFAULT_SHADOW_COLOR);
      mPaintShadow.setShadowLayer(mShadowRadiusSize, 0, mShadowDepth, getDarkerColor(mShadowRGB));
    } else if (((ImageView) view).getDrawable() instanceof ColorDrawable) {
      mShadowRGB = ((ColorDrawable) ((ImageView) view).getDrawable()).getColor();
      mPaintShadow.setShadowLayer(MAX_SHADOW_RADIUS_SIZE, 0, MAX_SHADOW_DEPTH, getDarkerColor(mShadowRGB));
    } else {
      mBitmap = ((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap();
      mSwatch = Palette.from(mBitmap).generate().getDominantSwatch();

      if (null != mSwatch) {
        mShadowRGB = mSwatch.getRgb();
      } else {
        mShadowRGB = Color.parseColor(DEFAULT_SHADOW_COLOR);
      }

      mPaintShadow.setShadowLayer(mShadowRadiusSize, 0, mShadowDepth, getDarkerColor(mShadowRGB));
      mBitmapTemp = Bitmap.createBitmap(mBitmap, 0, mBitmap.getHeight() / 4 * 3, mBitmap.getWidth(), mBitmap.getHeight() / 4);

      if (null != Palette.from(mBitmapTemp).generate().getDominantSwatch()) {
        mShadowRGB = Palette.from(mBitmapTemp).generate().getDominantSwatch().getRgb();
        mPaintShadow.setShadowLayer(mShadowRadiusSize, 0, mShadowDepth, mShadowRGB);
      }
    }
    mRectF = new RectF(view.getX() + (view.getWidth() / 20), view.getY(), view.getX() + view.getWidth() - (view.getWidth() / 20), view.getY() + view.getHeight());
    canvas.drawRoundRect(mRectF, mCornerRadius, mCornerRadius, mPaintShadow);
    canvas.restore();
    ((CustomImageView) view).setCornerRadius(mCornerRadius);
    super.dispatchDraw(canvas);
  }

  public int getDarkerColor(int color) {
    float[] hsv = new float[3];
    Color.colorToHSV(color, hsv);
    hsv[1] = hsv[1] + 0.1f;
    hsv[2] = hsv[2] - 0.1f;
    int darkerColor = Color.HSVToColor(hsv);
    return darkerColor;
  }

  public void setCornerRadius(int radius) {
    this.mCornerRadius = radius;
    invalidate();
  }

}
