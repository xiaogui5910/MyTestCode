package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.AnimUtils;


/**
 * 焦点框控件
 * @author jiangyuchen
 * @date 2014-2-13
 */
public class BorderView extends ImageView implements AnimationListener {
  protected static final String TAG = "BestSetting.BorderView";
  private static int BORDER_SIZE = 20;
  private static int TRAN_DUR_ANIM = 250;
  private SoundPool sp;
  private Context mContext;
  public BorderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
  }

  /**
   * 设置边界框的外框大小
   * @param size
   */
  public void setBorderSize(int size){
    BORDER_SIZE = size;
  }


  /**
   * 设置位移动画时间
   * @param dur
   */
  public void setTranslateAnimtionDuration(int dur){
    TRAN_DUR_ANIM = dur;
  }

  public void setLocation(View view){
    ViewLocation location = findLocationWithView(view);
//      Log.v(TAG, "setLocation X:"+location.x+" Y:"+location.y);
    mLeft = location.x-(int)BORDER_SIZE;
    mTop = location.y-(int)BORDER_SIZE;
    mRight = location.x+(int)BORDER_SIZE+view.getWidth();
    mBottom = location.y+(int)BORDER_SIZE+view.getHeight();
    this.layout(mLeft, mTop, mRight, mBottom);
    this.clearAnimation();
    BorderView.this.setVisibility(View.VISIBLE);
  }

  private int mLeft, mTop, mRight, mBottom;

  @Override
  protected void onLayout(boolean changed, int left, int top, int right,
                          int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    if(this.mLeft != left || mTop != top || mRight != right || mBottom != bottom){
      this.layout(this.mLeft, this.mTop, this.mRight, this.mBottom);
    }
  }

  /**
   * 获取View的位置
   * @param view 获取的控件
   * @return 位置
   */
  public ViewLocation findLocationWithView(View view){
    int[] location = new int[2];
    view.getLocationOnScreen(location);
    return new ViewLocation(location[0], location[1]);
  }

  private AnimationDrawable  mBoxBgAnim;
  /**
   * 初始化焦点框动画
   */
  public void runBorderAnimation(){
    this.setBackgroundResource(R.drawable.box_normal);
    restartBoxAnim();
  }

  /**
   * 重启闪烁动画
   *
   */
  public void restartBoxAnim(){
    BorderView.this.setVisibility(View.VISIBLE);
    this.clearAnimation();
    if(mBoxBgAnim == null){
      mBoxBgAnim = (AnimationDrawable) this.getBackground();
    }
    if(mBoxBgAnim.isRunning()){
      mBoxBgAnim.stop();
    }
    mBoxBgAnim.start();
    this.startAnimation(AnimUtils.buildAnimBoxNormal(mContext));
  }

  /**
   * 记录上一次的焦点组件，用于判断是否未移动控件的焦点，相同则不重新加载动画
   */
  private View mLastFocusView;
  /**
   * 启动焦点框位移动画
   */
  public void runTranslateAnimation(View toView) {
    runBorderAnimation();
    if(toView == null || mLastFocusView == toView){
      return;
    }
    //缩放比例
    float scaleWValue =  (float) this.getWidth()/((float) toView.getWidth()+2*BORDER_SIZE);
    float scaleHValue =  (float) this.getHeight()/((float) toView.getHeight()+2*BORDER_SIZE);
    ScaleAnimation scale = new ScaleAnimation( scaleWValue, 1.0f,
            scaleHValue,1.0f);
    //记录位置信息，以为启动动画前box已经设置到目标位置了。
    ViewLocation fromLocation = findLocationWithView(this);
    ViewLocation toLocation = findLocationWithView(toView);
    TranslateAnimation tran = new TranslateAnimation(-toLocation.x+(float)BORDER_SIZE+fromLocation.x, 0,
            -toLocation.y+(float)BORDER_SIZE+fromLocation.y, 0);
//      Log.v("TAG","fromX:"+(-toLocation.x+(float)BORDER_SIZE+fromLocation.x)+" fromY:"+(-toLocation.y+(float)BORDER_SIZE+fromLocation.y));
//      Log.v("TAG","fromX:"+fromLocation.x+ " toX:" +toLocation.x+" fromY:"+fromLocation.y+" toY:"+toLocation.x);
//      TranslateAnimation tran = new TranslateAnimation(0, toLocation.x-(float)BORDER_SIZE-fromLocation.x,
//              0, toLocation.y-(float)BORDER_SIZE-fromLocation.y);
    AnimationSet boxAnimaSet = new AnimationSet(true);
    boxAnimaSet.setAnimationListener(this);
    boxAnimaSet.addAnimation(scale);
    boxAnimaSet.addAnimation(tran);
    boxAnimaSet.setDuration(TRAN_DUR_ANIM);
    BorderView.this.setVisibility(View.INVISIBLE);
    setLocation(toView);//先位移到目标位置再启动动画
    Log.v(TAG, "setLocation runTranslateAnimation");
    BorderView.this.startAnimation(boxAnimaSet);
    mLastFocusView = toView;
  }

  public void playClickOgg(){
    if(sp == null){
      sp =new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
//      sp.load(mContext, R.raw.djtx, 0);
    }
    sp.play(1, 1, 1, 0, 0, 1);
  }
  private  static AnimationSet mBoxAnimClick;
  private void runClickAnimtion(){
    playClickOgg();
    if(mBoxAnimClick == null){
      mBoxAnimClick = AnimUtils.buildAnimBoxClick(mContext);
    }
    BorderView.this.startAnimation(mBoxAnimClick);
    notifyRestartBoxAnim(500);
  }

  public static final int MSG_BOX_BG_ANIM = 10;
  public static final int MSG_BOX_CLICK_ANIM = 11;

  /**
   * 重启背景动画
   * @param delay 延迟时间毫秒
   */
  void notifyRestartBoxAnim(int delay){
    mBoxHandler.sendEmptyMessageDelayed(MSG_BOX_BG_ANIM, delay);
  }
  /**
   * 点击动画
   */
  public void notifyClickBoxAnim(){
    mBoxHandler.sendEmptyMessageDelayed(MSG_BOX_CLICK_ANIM, 10);
  }

  Handler mBoxHandler = new Handler(){
    public void handleMessage(android.os.Message msg) {
      switch(msg.what){
        case MSG_BOX_BG_ANIM:
          restartBoxAnim();
          break;
        case MSG_BOX_CLICK_ANIM:
          runClickAnimtion();
          break;
      }
    };
  };

  @Override
  public void onAnimationEnd(Animation arg0) {
    notifyRestartBoxAnim(0);
  }

  @Override
  public void onAnimationRepeat(Animation arg0) {

  }

  @Override
  public void onAnimationStart(Animation arg0) {

  }
}