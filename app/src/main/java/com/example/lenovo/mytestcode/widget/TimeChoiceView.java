package com.example.lenovo.mytestcode.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.ToastUtil;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * Created by lenovo on 2017/1/12.
 */

public class TimeChoiceView extends RelativeLayout implements View.OnClickListener {

  private TextView tvTime1;
  private TextView tvTime2;
  private TextView tvTime3;
  private TextView tvTime4;
  private TextView tvTopTime1;
  private TextView tvTopTime2;
  private TextView tvTopTime3;
  private TextView tvTopTime4;
  private ImageView ivCircle;

  ViewDragHelper viewDragHelper;
  private int tvWidth;
  private int tvHeight;
  private int ivHeight;
  private int ivWidth;
  private int ivLeft;
  private int deltaWidth;
  private float downX;
  private float downY;
  private int clickLeft;
  private int currentLeft;
  private int startLeft;
  private TextView currentTopTextView;
  private TextView cli;

  public TimeChoiceView(Context context) {
    super(context);
    initView();
  }

  public TimeChoiceView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView();
  }

  public TimeChoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView();
  }

  private void initView() {
//    viewDragHelper = ViewDragHelper.create(this,callback);
    //生成要添加的子View
    View view = View.inflate(getContext(), R.layout.view_item_choice, null);
    addView(view);
    //底层文字
    tvTime1 = (TextView) findViewById(R.id.tv_time1);
    tvTime2 = (TextView) findViewById(R.id.tv_time2);
    tvTime3 = (TextView) findViewById(R.id.tv_time3);
    tvTime4 = (TextView) findViewById(R.id.tv_time4);
    //顶层文字
    tvTopTime1 = (TextView) findViewById(R.id.tv_top_time1);
    tvTopTime2 = (TextView) findViewById(R.id.tv_top_time2);
    tvTopTime3 = (TextView) findViewById(R.id.tv_top_time3);
    tvTopTime4 = (TextView) findViewById(R.id.tv_top_time4);
    //圆形图片
    ivCircle = (ImageView) findViewById(R.id.iv_circle);
    tvTime1.setOnClickListener(this);
    tvTime2.setOnClickListener(this);
    tvTime3.setOnClickListener(this);
    tvTime4.setOnClickListener(this);
    ivCircle.setOnClickListener(this);
    //默认30分钟
    tvTopTime2.setVisibility(View.VISIBLE);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    tvWidth = tvTime1.getMeasuredWidth();
    tvHeight = tvTime1.getMeasuredHeight();
    Log.e(TAG, "onSizeChanged: tvWidth=" + tvWidth + ",tvHeight=" + tvHeight);
    ivWidth = ivCircle.getMeasuredWidth();
    ivHeight = ivCircle.getMeasuredHeight();
    Log.e(TAG, "onSizeChanged: ivWidth=" + ivWidth + ",ivHeight=" + ivHeight);

    //图标离父view的左边的距离
    deltaWidth = tvWidth / 2 - ivWidth / 2;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    ivLeft = deltaWidth + tvWidth * 1;
    ivCircle.layout(ivLeft, 0, ivLeft + ivWidth, ivHeight);

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        downX = event.getX();
        downY = event.getY();

        break;
      case MotionEvent.ACTION_MOVE:
        float moveX = event.getX();
        float moveY = event.getY();
        float deltaX = moveX - downX;
        float deltaY = moveY - downY;

        break;
      case MotionEvent.ACTION_UP:
        float upX = event.getX();
        float upY = event.getY();
        break;
    }
    return super.onTouchEvent(event);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_time1:
        ToastUtil.showToast("time1");
        startAnim(0);
        break;
      case R.id.tv_time2:
        ToastUtil.showToast("time2");
        startAnim(1);
        break;
      case R.id.tv_time3:
        ToastUtil.showToast("time3");
        startAnim(2);
        break;
      case R.id.tv_time4:
        ToastUtil.showToast("time4");
        startAnim(3);
        break;
      case R.id.iv_circle:
        ToastUtil.showToast("circle");
        break;

    }
  }

  /**
   * 开始移动动画
   */
  private void startAnim(final int index) {
    //图标当前left
    currentLeft = ivCircle.getLeft();
    int currentImgIndex = getCurrentImgIndex(currentLeft);
    Log.e(TAG, "startAnim: currentLeft=" + currentLeft);
    //移动到点击位置clickLeft
    clickLeft = getImgLeft(index);
    //计算平移距离
    final int translationX = clickLeft - currentLeft;

    //获取当前顶部文字
    if (currentTopTextView == null) {
      currentTopTextView = getTopTextView(currentImgIndex);
    }
    //获取点击位置顶部文字
    final TextView clickTopTextView = getTopTextView(index);
    //计算开始动画左边距离
    Log.e(TAG, "startAnim: startLeft=" + startLeft);
    //显示点击位置的文本
//    showIndexTextView(index);
    ValueAnimator valueAnimator = ValueAnimator.ofInt(startLeft, translationX).setDuration(1000);
    valueAnimator.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        currentTopTextView.setVisibility(INVISIBLE);
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        startLeft = tvWidth * (index - 1);
        clickTopTextView.setVisibility(VISIBLE);
        currentTopTextView = clickTopTextView;
      }
    });
    valueAnimator.start();
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        int ratio = (int) animation.getAnimatedValue();
        ivCircle.setTranslationX(ratio);
      }
    });

  }

  /**
   * 显示指定索引文字
   *
   * @param index
   */
  private void showIndexTextView(int index) {
    hideAllTextView();
    getTopTextView(index).setVisibility(View.VISIBLE);
  }

  /**
   * 隐藏所有顶部文字
   */
  private void hideAllTextView() {
    tvTopTime1.setVisibility(View.INVISIBLE);
    tvTopTime2.setVisibility(View.INVISIBLE);
    tvTopTime3.setVisibility(View.INVISIBLE);
    tvTopTime4.setVisibility(View.INVISIBLE);
  }

  /**
   * 获取当前图标所以位置（索引）
   *
   * @param currentLeft
   * @return
   */
  private int getCurrentImgIndex(int currentLeft) {
    return (currentLeft - deltaWidth) / tvWidth;
  }

  /**
   * 根据索引获取顶部TextView
   *
   * @param index
   * @return
   */
  private TextView getTopTextView(int index) {
    switch (index) {
      case 0:
        return tvTopTime1;
      case 1:
        return tvTopTime2;
      case 2:
        return tvTopTime3;
      case 3:
        return tvTopTime4;

    }
    return null;
  }

  /**
   * 获取图标左边距离
   *
   * @param index
   * @return
   */
  private int getImgLeft(int index) {
    return deltaWidth + tvWidth * index;
  }


}
