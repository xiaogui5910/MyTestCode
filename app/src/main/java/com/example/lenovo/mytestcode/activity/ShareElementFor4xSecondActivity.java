package com.example.lenovo.mytestcode.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.adapter.AudioAdapter;
import com.example.lenovo.mytestcode.bean.Item;
import com.example.lenovo.mytestcode.bean.Status;
import com.example.lenovo.mytestcode.utils.ScreenUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShareElementFor4xSecondActivity extends AppCompatActivity {

  private static final TimeInterpolator DEFAULT_INTERPOLATOR = new OvershootInterpolator();
  private static final long DEFAULT_DURATION = 1000;
  private static final String TAG ="ShareSecondActivity" ;

  @Bind(R.id.rv_share_element)
  RecyclerView rvShareElement;
  private FrameLayout destinationView;
  private CardView containerView;
  private CardView card_view;
  private int originTopViewLeft;
  private int originTopViewTop;
  private int deltaX;
  private int deltaY;
  private float scaleX;
  private float scaleY;
  private int originTopViewWidth;
  private int originTopViewHeight;
  private int containerViewHeight;
  private int originBottomViewLeft;
  private int originBottomViewTop;
  private int originBottomViewWidth;
  private int originBottomViewHeight;
  private float bottomScaleX;
  private float bottomScaleY;
  private float bottomDeltaX;
  private float bottomDeltaY;
  private int llBottomHeight;
  private int llTopHeight;
  private int containViewTop;
  private String action;

  private ArrayList<Status> list;
  private LinearLayout llTop;
  private LinearLayout llBottom;
  private LinearLayout llBottomContain;
  private RelativeLayout rlActivity;
  private ImageView ivImg;
  private View headerView;
  private ImageView ivBottomFloatingViewImg;
  private ViewPager viewPager;
  private TabLayout tabLayout;
  private AudioAdapter audioAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_share_element_for4x_second);
    ButterKnife.bind(this);
    initView();

    initData();
  }

  private void initView() {
    rlActivity = (RelativeLayout) findViewById(R.id.rl_activity);
    headerView = getLayoutInflater().inflate(R.layout.share_element_rv_header, rlActivity,false);

//    destinationView = (CardView) headerView.findViewById(R.id.iv_detail);
    destinationView = (FrameLayout) headerView.findViewById(R.id.iv_detail);

    containerView = (CardView) findViewById(R.id.container_detail);
    llTop = (LinearLayout) headerView.findViewById(R.id.ll_top);
    llBottom = (LinearLayout) headerView.findViewById(R.id.ll_bottom);
    viewPager = (ViewPager) headerView.findViewById(R.id.viewpager);
    tabLayout = (TabLayout) headerView.findViewById(R.id.sliding_tabs);
//    llBottomContain = (LinearLayout) headerView.findViewById(R.id.ll_bottom_contain);
    llBottomContain = (LinearLayout)findViewById(R.id.ll_bottom_contain);
//    ivImg = (ImageView) headerView.findViewById(R.id.iv_img);
    ivBottomFloatingViewImg = (ImageView) findViewById(R.id.iv_bottom_floating_view_img);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        viewPager.setCurrentItem(1);
      }
    },5000);
  }

  private void initData() {
    audioAdapter = new AudioAdapter(this);
    viewPager.setAdapter(audioAdapter);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabMode(TabLayout.MODE_FIXED);
    //获取屏幕宽高
    int screenWidth = ScreenUtils.getScreenWidth(this);
    int screenHeight = ScreenUtils.getScreenHeight(this);
    //设置头布局填充整个屏幕
    ViewGroup.LayoutParams params = headerView.getLayoutParams();
    params.width = screenWidth;
    params.height = screenHeight;
    headerView.setLayoutParams(params);

    list = new ArrayList<>();
    for (int i = 'A'; i < 'Z'; i++) {
      String name = "" + (char) i;
      String desc = "" + (char) i + "=desc";
      int icon = R.mipmap.ic_launcher;

      Status status = new Status(name, desc, icon);
      list.add(status);
    }
    //使RecyclerView保持固定的大小，该信息被用于自身的优化
//    rvShareElement.setHasFixedSize(true);

    rvShareElement.setLayoutManager(new LinearLayoutManager(this));
    ShareElementFor4xSecondActivity.QuickAdapter adapter = new ShareElementFor4xSecondActivity.QuickAdapter();

    //开启加载动画
//    adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
    //默认动画每个item只执行一次,如果想重复执行动画可以调用一下方法
//    adapter.isFirstOnly(false);

    //给recyclerview设置adapter
    rvShareElement.setAdapter(adapter);

    //设置空布局
    adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.emptyview, (ViewGroup) rvShareElement.getParent(), false));

    //添加头布局
//    adapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.share_element_rv_header, (ViewGroup) rvShareElement.getParent(), false));
    adapter.addHeaderView(headerView);

    //    card_view = (CardView) findViewById(R.id.card_view);
    // 取出传递过来的originView信息
    extractViewInfoFromBundle(getIntent());
    //简单起见，我们直接设置本地图片 不使用网络加载图片
    Item item = (Item) getIntent().getSerializableExtra("item");
//    ivImg.setImageResource(item.getImg());
//    destinationView.setBackgroundDrawable(getResources().getDrawable(R.drawable.test2));

    onUiReady();
  }

  public class QuickAdapter extends BaseQuickAdapter<Status> {
    public QuickAdapter() {
      super(R.layout.item2, list);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Status item) {
      viewHolder.setText(R.id.tv_name, item.getName())
              .setText(R.id.tv_desc, item.getDesc())
              .addOnClickListener(R.id.tv_name)
              .addOnClickListener(R.id.tv_desc)
              .addOnClickListener(R.id.iv_icon)
              .setBackgroundRes(R.id.iv_icon, item.getIcon());
//      Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
  }

  /**
   * 取出传递过来的originView信息
   *
   * @param intent
   */
  private void extractViewInfoFromBundle(Intent intent) {
    action = intent.getAction();

    if (ShareElementFor4xActivity.ACTION_BOTTOM_CLICK.equals(action)) {
      //底部数据初始化
      Bundle bundle = intent.getBundleExtra(ShareElementFor4xActivity.VIEW_INFO_EXTRA_BOTTOM);
      originBottomViewLeft = bundle.getInt("left1", 0);
      originBottomViewTop = bundle.getInt("top1", 0);
      originBottomViewWidth = bundle.getInt("width1", 100);
      originBottomViewHeight = bundle.getInt("height1", 100);
      containViewTop = bundle.getInt("contain_top", 100);
      Log.e("Share44444", "createViewInfoBundle: left+top+width+height" + originBottomViewLeft +
              " ," + originBottomViewTop + " ," + originBottomViewWidth + " ," + originBottomViewHeight);
    } else if (ShareElementFor4xActivity.ACTION_IMG_CLICK.equals(action)) {
      //点击图片数据初始化
      Bundle bundle = intent.getBundleExtra(ShareElementFor4xActivity.VIEW_INFO_EXTRA);
      originTopViewLeft = bundle.getInt("left", 0);
      originTopViewTop = bundle.getInt("top", 0);
      originTopViewWidth = bundle.getInt("width", 100);
      originTopViewHeight = bundle.getInt("height", 100);
      Log.e("Share33333", "createViewInfoBundle: left+top+width+height" + originTopViewLeft +
              " ," + originTopViewTop + " ," + originTopViewWidth + " ," + originTopViewHeight);

      originBottomViewLeft = bundle.getInt("left1", 0);
      originBottomViewTop = bundle.getInt("top1", 0);
      originBottomViewWidth = bundle.getInt("width1", 100);
      originBottomViewHeight = bundle.getInt("height1", 100);
      containViewTop = bundle.getInt("contain_top", 100);
      Log.e("Share44444", "createViewInfoBundle: left+top+width+height" + originBottomViewLeft +
              " ," + originBottomViewTop + " ," + originBottomViewWidth + " ," + originBottomViewHeight);
    }
  }

  private void onUiReady() {
    destinationView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override
      public boolean onPreDraw() {
        // remove previous listener
        destinationView.getViewTreeObserver().removeOnPreDrawListener(this);
        //准备场景
        prepareScene();
        //播放动画
        runEnterAnimation();
        return true;
      }
    });
  }

  private void prepareScene() {
    if (ShareElementFor4xActivity.ACTION_BOTTOM_CLICK.equals(action)) {
      prepareSceneData(originBottomViewWidth, originBottomViewHeight, originBottomViewLeft, originBottomViewTop);
    } else if (ShareElementFor4xActivity.ACTION_IMG_CLICK.equals(action)) {
      prepareSceneData(originTopViewWidth, originTopViewHeight, originTopViewLeft, originTopViewTop);
    }
  }

  private void prepareSceneData(int originWidth, int originHeight, int originLeft, int originTop) {
    //缩放到顶部起始view大小
    scaleX = (float) originWidth / destinationView.getWidth();
    scaleY = (float) originHeight / destinationView.getHeight();

    destinationView.setScaleX(scaleX);
    destinationView.setScaleY(scaleY);

    int[] screenLocation = new int[2];
//    destinationView.getLocationOnScreen(screenLocation);
    destinationView.getLocationInWindow(screenLocation);
    Log.e("prepareScene", "prepareScene: screenLocation" + screenLocation[0] + " ," + screenLocation[1]);
    //移动到顶部起始view位置
    deltaX = originLeft - screenLocation[0];
    deltaY = originTop - screenLocation[1];
    destinationView.setTranslationX(deltaX);
    destinationView.setTranslationY(deltaY);
  }

  private void runEnterAnimation() {
    destinationView.setVisibility(View.VISIBLE);
    //获取图片的颜色，设置背景色
    Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.test2)).getBitmap();
    Palette p = Palette.generate(bitmap);
    Palette.Swatch swatch = p.getDarkVibrantSwatch();
//    containerView.setBackgroundColor(swatch.getRgb());
    //执行动画
//    ViewPropertyAnimator animator = destinationView.animate();
//    animator.setDuration(DEFAULT_DURATION)
////            .setInterpolator(DEFAULT_INTERPOLATOR)
//            .scaleX(1f)
//            .scaleY(1f)
//            .translationX(0)
//            .translationY(0)
//            .start();
//中间图片平移和缩放动画
    ObjectAnimator translationXOA = ObjectAnimator.ofFloat(destinationView, "translationX", deltaX, 0f);
    ObjectAnimator translationYOA = ObjectAnimator.ofFloat(destinationView, "translationY", deltaY, 0f);
    ObjectAnimator scaleXOA = ObjectAnimator.ofFloat(destinationView, "scaleX", scaleX, 1f);
    ObjectAnimator scaleYOA = ObjectAnimator.ofFloat(destinationView, "scaleY", scaleY, 1f);

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.play(translationXOA).with(translationYOA).with(scaleXOA).with(scaleYOA);
    animatorSet.setDuration(DEFAULT_DURATION);
    animatorSet.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
        long duration = animation.getDuration();
        Log.e("onAnimationRepeat:"," duration="+duration );
        super.onAnimationRepeat(animation);
      }

      @Override
      public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
      }
    });
    //开始动画
    animatorSet.start();

    ValueAnimator animator = ValueAnimator.ofInt(0, 70);
    animator.setDuration(1000).start();
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      @Override
      public void onAnimationUpdate(ValueAnimator animation)
      {
        int animatedValue = (int) animation.getAnimatedValue();
        Log.e(TAG, "onAnimationUpdate:animatedValue= "+animatedValue );
//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) viewPager.getLayoutParams();
        viewPager.setPadding(animatedValue,0,animatedValue,0);
      }
    });
    //背景白色平移和透明动画
    containerViewHeight = containerView.getHeight();

    startTranslationAlphaAnimator(containerView, containViewTop, 0f);

    //顶部和底部动画  延迟500ms执行
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        startTopAndBottomAnim();
      }
    }, 500);
  }

  /**
   * 执行进入平移透明度动画
   */
  private void startTranslationAlphaAnimator(View translationView, float startTranslationY, float endTranslationY) {
    ObjectAnimator oa = ObjectAnimator.ofFloat(translationView, "translationY", startTranslationY, endTranslationY);
    ObjectAnimator alphaOA = ObjectAnimator.ofFloat(translationView, "alpha", 0.8f, 1f);

    AnimatorSet animatorSet = new AnimatorSet();
//    animatorSet.play(oa).with(alphaOA);
    animatorSet.play(oa);

    animatorSet.setDuration(DEFAULT_DURATION);
    animatorSet.start();
  }

  /**
   * 执行退出平移透明度动画
   */
  private void startExitTranslationAlphaAnimator(View translationView, float startTranslationY, float endTranslationY) {
    ObjectAnimator oa = ObjectAnimator.ofFloat(translationView, "translationY", startTranslationY, endTranslationY);
    ObjectAnimator alphaOA = ObjectAnimator.ofFloat(translationView, "alpha", 1f, 0f);

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.play(oa).with(alphaOA);

    animatorSet.setDuration(500);
    animatorSet.start();
  }

  /**
   * 顶部和底部动画
   */
  private void startTopAndBottomAnim() {
    llTop.setVisibility(View.VISIBLE);
    llBottom.setVisibility(View.VISIBLE);
    llTopHeight = llTop.getHeight();
    llBottomHeight = llBottom.getHeight();

    startTranslationAlphaAnimator(llTop, -llTopHeight / 2, 0f);
    startTranslationAlphaAnimator(llBottom, llBottomHeight, 0f);
  }

  @Override
  public void onBackPressed() {
//    super.onBackPressed();
    llBottomContain.setVisibility(View.VISIBLE);
    prepareExitScene();
    runExitAnimation();
  }

  private void prepareExitScene() {

    //退出时缩放到底部起始view大小
    bottomScaleX = (float) originBottomViewWidth / destinationView.getWidth();
    bottomScaleY = (float) originBottomViewHeight / destinationView.getHeight();

    //退出时移动到底部起始view位置
//    bottomDeltaX = (originBottomViewLeft -originScreenLocation[0])+(destinationView.getWidth()*(1-bottomScaleX))/2;
//    bottomDeltaY = (originBottomViewTop -originScreenLocation[1])*(destinationView.getHeight()*(1-bottomScaleY))/2;
    destinationView.setScaleX(bottomScaleX);
    destinationView.setScaleY(bottomScaleY);

    //获取未改变大小的destinationView的位置
    int[] originScreenLocation = new int[2];
//    destinationView.getLocationOnScreen(originScreenLocation);
    destinationView.getLocationOnScreen(originScreenLocation);
    Log.e("prepareExitScene", "prepareExitScene: originScreenLocation" + originScreenLocation[0] + " ," + originScreenLocation[1]);

    bottomDeltaX = originBottomViewLeft - originScreenLocation[0];
    bottomDeltaY = originBottomViewTop - originScreenLocation[1];
  }


  private void runExitAnimation() {

    //中间图片动画
    ObjectAnimator translationXOA = ObjectAnimator.ofFloat(destinationView, "translationX", 0f, bottomDeltaX);
    ObjectAnimator translationYOA = ObjectAnimator.ofFloat(destinationView, "translationY", 0f, bottomDeltaY);
    ObjectAnimator scaleXOA = ObjectAnimator.ofFloat(destinationView, "scaleX", 1f, bottomScaleX);
    ObjectAnimator scaleYOA = ObjectAnimator.ofFloat(destinationView, "scaleY", 1f, bottomScaleY);

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.play(translationXOA).with(translationYOA).with(scaleXOA).with(scaleYOA);

    animatorSet.setDuration(DEFAULT_DURATION);
    animatorSet.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            llTop.setVisibility(View.INVISIBLE);
          }
        }, DEFAULT_DURATION / 2);
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        finish();
        overridePendingTransition(0, 0);
      }

      @Override
      public void onAnimationCancel(Animator animation) {

      }

      @Override
      public void onAnimationRepeat(Animator animation) {

      }
    });
    animatorSet.start();
    ValueAnimator animator = ValueAnimator.ofInt(70, 0);
    animator.setDuration(1000).start();
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      @Override
      public void onAnimationUpdate(ValueAnimator animation)
      {
        int animatedValue = (int) animation.getAnimatedValue();
        Log.e(TAG, "onAnimationUpdate:animatedValue= "+animatedValue );
//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) viewPager.getLayoutParams();
        viewPager.setPadding(animatedValue,0,animatedValue,0);
        Log.e(TAG, "onAnimationUpdate: viewPager.getPaddingLeft()="+viewPager.getPaddingLeft() );

      }
    });

    //背景白色平移和透明动画
//    ObjectAnimator oa = ObjectAnimator.ofFloat(containerView, "translationY", 0f, containViewTop);
    ObjectAnimator oa = ObjectAnimator.ofFloat(containerView, "translationY", 0f, containViewTop);
    ObjectAnimator llTopOA = ObjectAnimator.ofFloat(llTop, "translationY", 0f, containViewTop);
    ObjectAnimator rvShareElementOA = ObjectAnimator.ofFloat(rvShareElement, "translationY", 0f, containViewTop);
//    ObjectAnimator llTopAlphaOA = ObjectAnimator.ofFloat(llTop, "alpha", 0.3f,0f);
    ObjectAnimator alphaOA = ObjectAnimator.ofFloat(llBottomContain, "alpha", 0f, 1f);
    AnimatorSet animatorSet1 = new AnimatorSet();
//    animatorSet1.play(oa).with(llTopOA).with(alphaOA).with(rvShareElementOA);
    animatorSet1.play(oa).with(llTopOA).with(alphaOA);

    animatorSet1.setDuration(DEFAULT_DURATION);
    animatorSet1.start();

    ObjectAnimator llTopAlphaOA = ObjectAnimator.ofFloat(llTop, "alpha", 1f, 0f);
    llTopAlphaOA.setDuration(500);
    llTopAlphaOA.start();

//    startExitTranslationAlphaAnimator(llTop, 0f, llTopHeight / 2);
    startExitTranslationAlphaAnimator(llBottom, 0f, llBottomHeight);
  }
}
