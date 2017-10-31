package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lenovo.mytestcode.R;

/**
 * Created by ccg on 2017/8/11.
 */

public class ReflectImageView extends View {

  private Bitmap bitmap;
  private Paint paint;
  private int x;
  private int y;
  private Bitmap srcBitmap;
  private PorterDuffXfermode xfermode;

  public ReflectImageView(Context context) {
    this(context,null);
  }

  public ReflectImageView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public ReflectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    bitmap = decodeBitmapFromRes(getResources(),R.drawable.pic_6,300,350);
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    int dstW = bitmap.getWidth();
    int dstH = bitmap.getHeight();
    Matrix matrix = new Matrix();
    matrix.setScale(1f,-1f);
    srcBitmap = Bitmap.createBitmap(this.bitmap, 0, 0, dstW, dstH, matrix, true);
    int sw = getResources().getDisplayMetrics().widthPixels;
    x = sw/2-dstW/2;
    y = 0;
    xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    LinearGradient linearGradient = new LinearGradient(x,bitmap.getHeight(),x,bitmap.getHeight()+bitmap.getHeight()/2,0xDD000000,Color.TRANSPARENT, Shader.TileMode.CLAMP);
    paint.setShader(linearGradient);
  }

  private Bitmap decodeBitmapFromRes(Resources resources, int resId, int targetW, int targetH) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.RGB_565;
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(resources,resId, options);

    int inSampleSize = calculateInSampleSize(bitmap,targetW,targetH,options);
    options.inJustDecodeBounds = false;
    options.inSampleSize = inSampleSize;

    return BitmapFactory.decodeResource(resources,resId,options);
  }

  private int calculateInSampleSize(Bitmap bitmap, int targetW, int targetH, BitmapFactory.Options options) {
    if (targetH<=0 ||targetW<=0){
      return 1;
    }
    int inSampleSize =1;
    int width = options.outWidth;
    int height = options.outHeight;
    if (width>targetW || height>targetH){
      int halfW= width/2;
      int halfH = height/2;
      while (halfH/inSampleSize>targetH && halfW/inSampleSize>targetW){
        inSampleSize*=2;
      }
    }
    return inSampleSize;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int wMode = MeasureSpec.getMode(widthMeasureSpec);
    int hMode = MeasureSpec.getMode(heightMeasureSpec);
    int wSize = MeasureSpec.getSize(widthMeasureSpec);
    int hSize = MeasureSpec.getSize(heightMeasureSpec);

    if (wMode==MeasureSpec.AT_MOST&&hMode==MeasureSpec.AT_MOST){
      setMeasuredDimension(300,300);
    }else if (wMode==MeasureSpec.AT_MOST){
      setMeasuredDimension(300,hSize);
    }else if (hMode==MeasureSpec.AT_MOST){
      setMeasuredDimension(wSize,300);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //背景黑色
    canvas.drawColor(Color.BLACK);
    //绘制原图
    canvas.drawBitmap(bitmap,x,y,paint);
    //绘制倒影图
    canvas.drawBitmap(srcBitmap,x,bitmap.getHeight(),null);

    //绘制图层
    paint.setXfermode(xfermode);

    canvas.drawRect(x,bitmap.getHeight(),x+bitmap.getWidth(),bitmap.getHeight()*2,paint);
    paint.setXfermode(null);
  }
}
