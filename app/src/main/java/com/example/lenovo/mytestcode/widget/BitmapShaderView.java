package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lenovo.mytestcode.R;

/**
 * Created by ccg on 2017/8/10.
 */

public class BitmapShaderView extends View {

  private Paint paint;

  public BitmapShaderView(Context context) {
    super(context);
    init();
  }

  public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_7);
    BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    paint = new Paint();
    paint.setShader(bitmapShader);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int radius = Math.min(getWidth(),getHeight())/2;
    canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
  }
}
