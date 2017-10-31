package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lenovo.mytestcode.R;

/**
 * Created by ccg on 2017/7/31.
 */

public class CheckView extends View {
  private Context mContext;
  private Paint mPaint;
  private int mWidth;
  private int mHeight;
  private Bitmap okBitmap;
  private Rect src;
  private Rect dst;

  private static final int STATE_NULL = 1;
  private static final int STATE_CHENCK = 2;
  private static final int STATE_UNCHENCK = 3;

  private int animCurrentPage = -1;
  private int animMaxPage = 13;
  private int animState = STATE_NULL;
  private int animDuration = 500;

  private boolean isCheck;
  private Handler mHandler;


  public CheckView(Context context) {
    this(context, null);
  }

  public CheckView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    this.mContext = context;

    mPaint = new Paint();
    mPaint.setColor(Color.YELLOW);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setAntiAlias(true);

    okBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.checkmark);
    src = new Rect();
    dst = new Rect(-200, -200, 200, 200);

    mHandler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (animCurrentPage >= 0 && animCurrentPage < animMaxPage) {
          invalidate();
          if (animState == STATE_NULL) {
            return;
          }
          if (animState == STATE_CHENCK) {
            animCurrentPage++;
          } else if (animState == STATE_UNCHENCK) {
            animCurrentPage--;
          }
          this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        } else {
          if (isCheck) {
            animCurrentPage = animMaxPage - 1;
          } else {
            animCurrentPage = -1;
          }
          invalidate();
          animState = STATE_NULL;
        }
      }
    };
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
    //平移画布到中心
    canvas.translate(mWidth / 2, mHeight / 2);
    //绘制外圆，圆心（0,0）半径240
    canvas.drawCircle(0, 0, 240, mPaint);

    int sideLength = okBitmap.getHeight();
    src.left = animCurrentPage * sideLength;
    src.top = 0;
    src.right = (animCurrentPage + 1) * sideLength;
    src.bottom = sideLength;
    //绘制指定src和dst的图形
    canvas.drawBitmap(okBitmap, src, dst, null);
  }

  public void check() {
    if (animState != STATE_NULL || isCheck) {
      return;
    }
    animCurrentPage = 0;
    animState = STATE_CHENCK;
    isCheck = true;
    mHandler.sendEmptyMessageDelayed(0,animDuration/animMaxPage);
  }

  public void unCheck() {
    if (animState != STATE_NULL || !isCheck) {
      return;
    }
    animCurrentPage = animMaxPage - 1;
    animState = STATE_UNCHENCK;
    isCheck = false;
    mHandler.sendEmptyMessageDelayed(0,animDuration/animMaxPage);
  }

  public void setAnimDuration(int animDuration) {
    if (animDuration<=0){
      return;
    }
    this.animDuration = animDuration;
  }

  public void setBackColor(int color) {
    mPaint.setColor(color);
  }
}
//public class CheckView extends View {
//
//  private static final int ANIM_NULL = 0;         //动画状态-没有
//  private static final int ANIM_CHECK = 1;        //动画状态-开启
//  private static final int ANIM_UNCHECK = 2;      //动画状态-结束
//
//  private Context mContext;           // 上下文
//  private int mWidth, mHeight;        // 宽高
//  private Handler mHandler;           // handler
//
//  private Paint mPaint;
//  private Bitmap okBitmap;
//
//  private int animCurrentPage = -1;       // 当前页码
//  private int animMaxPage = 13;           // 总页数
//  private int animDuration = 500;         // 动画时长
//  private int animState = ANIM_NULL;      // 动画状态
//
//  private boolean isCheck = false;        // 是否只选中状态
//
//  public CheckView(Context context) {
//    super(context, null);
//
//  }
//
//  public CheckView(Context context, AttributeSet attrs) {
//    super(context, attrs);
//    init(context);
//  }
//
//  /**
//   * 初始化
//   * @param context
//   */
//  private void init(Context context) {
//    mContext = context;
//
//    mPaint = new Paint();
//    mPaint.setColor(0xffFF5317);
//    mPaint.setStyle(Paint.Style.FILL);
//    mPaint.setAntiAlias(true);
//
//    okBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.checkmark);
//
//    mHandler = new Handler() {
//      @Override
//      public void handleMessage(Message msg) {
//        super.handleMessage(msg);
//        if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
//          invalidate();
//          if (animState == ANIM_NULL)
//            return;
//          if (animState == ANIM_CHECK) {
//
//            animCurrentPage++;
//          } else if (animState == ANIM_UNCHECK) {
//            animCurrentPage--;
//          }
//
//          this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
//          Log.e("AAA", "Count=" + animCurrentPage);
//        } else {
//          if (isCheck) {
//            animCurrentPage = animMaxPage - 1;
//          } else {
//            animCurrentPage = -1;
//          }
//          invalidate();
//          animState = ANIM_NULL;
//        }
//      }
//    };
//  }
//
//
//  /**
//   * View大小确定
//   * @param w
//   * @param h
//   * @param oldw
//   * @param oldh
//   */
//  @Override
//  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//    super.onSizeChanged(w, h, oldw, oldh);
//    mWidth = w;
//    mHeight = h;
//  }
//
//  /**
//   * 绘制内容
//   * @param canvas
//   */
//  @Override
//  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);
//
//    // 移动坐标系到画布中央
//    canvas.translate(mWidth / 2, mHeight / 2);
//
//    // 绘制背景圆形
//    canvas.drawCircle(0, 0, 240, mPaint);
//
//    // 得出图像边长
//    int sideLength = okBitmap.getHeight();
//
//    // 得到图像选区 和 实际绘制位置
//    Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
//    Rect dst = new Rect(-200, -200, 200, 200);
//
//    // 绘制
//    canvas.drawBitmap(okBitmap, src, dst, null);
//  }
//
//
//  /**
//   * 选择
//   */
//  public void check() {
//    if (animState != ANIM_NULL || isCheck)
//      return;
//    animState = ANIM_CHECK;
//    animCurrentPage = 0;
//    mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
//    isCheck = true;
//  }
//
//  /**
//   * 取消选择
//   */
//  public void unCheck() {
//    if (animState != ANIM_NULL || (!isCheck))
//      return;
//    animState = ANIM_UNCHECK;
//    animCurrentPage = animMaxPage - 1;
//    mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
//    isCheck = false;
//  }
//
//  /**
//   * 设置动画时长
//   * @param animDuration
//   */
//  public void setAnimDuration(int animDuration) {
//    if (animDuration <= 0)
//      return;
//    this.animDuration = animDuration;
//  }
//
//  /**
//   * 设置背景圆形颜色
//   * @param color
//   */
//  public void setBackgroundColor(int color){
//    mPaint.setColor(color);
//  }
//}
