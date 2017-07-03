package com.example.lenovo.mytestcode.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.BitmapUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class BlurActivity extends AppCompatActivity  {

  private RelativeLayout rlBgLayout;
  private LinearLayout llBlurLayout;
  private FrameLayout flCenter;
  private CircleImageView civCircle;
  private ImageView ivTest;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_blur);
    initView();
  }

  protected void initView() {
    rlBgLayout = (RelativeLayout) findViewById(R.id.rl_bg_layout);
    llBlurLayout = (LinearLayout) findViewById(R.id.ll_blur_layout);
    flCenter = (FrameLayout) findViewById(R.id.fl_center);
    civCircle = (CircleImageView) findViewById(R.id.civ_circle);
    ivTest = (ImageView) findViewById(R.id.iv_test);

    Bitmap bitmap = BitmapUtils.highlightImage(BitmapFactory.decodeResource(getResources(), R.drawable.pic_6));
    ivTest.setBackground(new BitmapDrawable(getResources(),bitmap));
//    rlBgLayout.setBackgroundResource(R.drawable.pic_6);
//    llBlurLayout.setBackgroundResource(R.drawable.test2);
//    setBlur();
//    final ViewTreeObserver viewTreeObserver = ivTest.getViewTreeObserver();
//    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//      @Override
//      public void onGlobalLayout() {
//        ivTest.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//        int height = ivTest.getHeight();
//        int width = ivTest.getWidth();
//        Bitmap halo = BitmapUtils.halo(BitmapFactory.decodeResource(getResources(), R.drawable.pic_6), 300,300, 100);
//        ivTest.setBackground(new BitmapDrawable(getResources(),halo));
//      }
//    });

  }

  protected void setBlur() {
    ViewTreeObserver vto = rlBgLayout.getViewTreeObserver();
    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

      @Override
      public void onGlobalLayout() {
        // 保证只调用一次
        rlBgLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        // 组件生成cache（组件显示内容）
        rlBgLayout.buildDrawingCache();
        // 得到组件显示内容
        Bitmap bitmap = rlBgLayout.getDrawingCache();
        // 局部模糊处理
//        BitmapUtils.blur(getApplicationContext(), bitmap, llBlurLayout, 5);
        BitmapUtils.blur(getApplicationContext(), bitmap, civCircle, 5);
      }
    });
  }
//
//
//  private static final String TAG_base = "BaseActivity";
//  BorderView mBorderView;
//  Handler mHandler = new Handler();
//
//  @Override
//  protected void onStart() {
//    super.onStart();
//    View view = findViewById(R.id.box);
//    if(view == null){
//      return;
////      throw new BoxNotFoundException();//必须在父布局中焦点框控件，否则抛出异常
//    }
//  }
//
//  public BorderView getBorderView() {
//    return mBorderView;
//  }
//
//  public void setBorderView(BorderView box) {
//    this.mBorderView = box;
//  }
//
//  public void setFocusedView(final View view, int delay){
//    if(mBorderView == null){
//      mBorderView = (BorderView)findViewById(R.id.box);
//    }
//    mBorderView.runBorderAnimation();
//    mHandler.postDelayed(new Runnable() {
//
//      @Override
//      public void run() {
//        if(view == null){
//          return;
//        }
//        view.requestFocus();
//        mBorderView.setLocation(view);
//      }
//    }, delay);
//  }
//
//  public void runClickAnim(){
//    this.getBorderView().notifyClickBoxAnim();
//  }
//
//  @Override
//  protected void onResume() {
//    super.onResume();
//    ivTest.setVisibility(View.VISIBLE);
//    setBorderView(new BorderView(this,null));
//    setFocusedView(ivTest,5000);
//    ivTest.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        ToastUtil.showToast("111");
//      }
//    });
//  }
//
//
//  @Override
//  public void onFocusChange(View v, boolean hasFocus) {
//    if(hasFocus){
//      mBorderView.runTranslateAnimation(v);
//    }
//  }
//  public void setClickListener(View v, View.OnClickListener listener){
//    v.setOnClickListener(listener);
//    v.setOnFocusChangeListener(this);
//  }
}
