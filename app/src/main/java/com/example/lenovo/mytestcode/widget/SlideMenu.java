package com.example.lenovo.mytestcode.widget;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 让SlideMenu集成FrameLayout原因是FrameLayout已经实现了onMeasure方法，
 * 我们就不需要实现了，并FrameLayout是最轻量级的布局
 *
 */
public class SlideMenu extends FrameLayout {

    ViewDragHelper viewDragHelper;
    private View menuView;
    private View mainView;

    private int dragRange;
    private int mainWidth;
    private int menuWidth;
    FloatEvaluator floatEvaluator = new FloatEvaluator();
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    public SlideMenu(Context context) {
        this(context,null);
    }
    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private DragState mState = DragState.Close;//表示当前的状态

    /**
     * 定义状态常量
     */
    public enum DragState{
        Open,Close
    }

    private void init(){
        viewDragHelper = ViewDragHelper.create(this,callback);
    }

    /**
     * 该方法在ViewGroup将子View全部添加进来之后执行，但是是在onMeasure之前执行
     * 所以该方法一般用来初始化子View的应用，但是还不能获取子View的宽高
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }

    /**
     * 当onMeasure方法执行完之后执行，在该方法中可以获取View的宽高
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mainWidth = mainView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();

        dragRange = (int) (getMeasuredWidth()*0.6f);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //让viewDragHelper帮助我们判断是否应该拦截
        boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //让ViewDragHelper帮助我们处理触摸事件
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        /**
         * 判断是否需要捕获View的触摸事件
         * @param child   当前触摸的View
         * @param pointerId  触摸点索引
         * @return  true:捕获，  false：不捕获
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child==mainView || child==menuView;
        }

        /**
         * 当一个View被捕获触摸事件时候调用
         * @param capturedChild 被捕获触摸事件的子View
         * @param activePointerId
         */
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
            Log.e("tag","onViewCaptured");
        }

        /**
         * 方法名为获取水平方向拖拽的范围，然而目前并没有用，该方法的返回值用来作为判断滑动方向的条件之一，
         * 如果你想水平移动，那么该方法的返回值最好大于0
         * @param child
         * @return
         */
        @Override
        public int getViewHorizontalDragRange(View child) {
            return 200;
        }


        /**
         * 用来修正或者指定子View在水平方向上的移动
         * @param child
         * @param left  是ViewDragHelper帮你计算好的View最新的left的值，left=view.getLeft()+dx
         * @param dx   本次水平移动的距离
         * @return  返回的值表示我们真正想让View的left变成的值
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //限制主界面的滑动
            if(child==mainView){
                left = clampLeft(left);
            }
            return left;
        }
        /**
         * 用来修正或者指定子View在垂直方向上的移动
         * @param
         * @param top  是ViewDragHelper帮你计算好的View最新的top的值，top=view.getTop()+dy
         * @param dy   本次垂直移动的距离
         * @return  返回的值表示我们真正想让View的top变成的值
         */
//        @Override
//        public int clampViewPositionVertical(View child, int top, int dy) {
//            return top;
//        }

        /**
         * 当View移动的时候调用
         * @param changedView   当前移动的VIew
         * @param left  当前View移动之后最新的left
         * @param top   当前View移动之后最新的top
         * @param dx    水平移动的距离
         * @param dy    垂直移动的距离
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
//            Log.e("tag","left: "+left   +"  dx: "+dx);
            //当menuView移动的时候，让mainView进行伴随移动,但是menuView保持不动
            if(changedView==menuView){
                //让menuView的位置固定住
                menuView.layout(0,0,menuWidth,menuView.getBottom());

                int newLeft = mainView.getLeft()+dx;
                //限制newLeft
                newLeft = clampLeft(newLeft);
                mainView.layout(newLeft,0,newLeft+mainWidth,mainView.getBottom());
            }

            //1.计算mainView滑动的百分比
            float fraction = mainView.getLeft()*1f/dragRange;
//            Log.e("tag",fraction+"");
            //2.根据fraction来执行一系列动画
            executeAnim(fraction);
            //3.回调监听器的方法
            if(fraction==1f && mState!= DragState.Open){
                //说明打开了
                mState = DragState.Open;
                if(listener!=null){
                    listener.onOpen();
                }
            }else if(fraction==0f && mState!= DragState.Close){
                //说明关闭了
                mState = DragState.Close;
                if(listener!=null){
                    listener.onClose();
                }
            }
            //拖拽中直接回调就行
            if(listener!=null){
                listener.onDragging(fraction);
            }

        }

        /**
         * 当手指从View上抬起的时候回调
         * @param releasedChild
         * @param xvel  x方向滑动的速度
         * @param yvel  y方向滑动的速度
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //判断mainView的left是否是大于一半
            if(mainView.getLeft()>dragRange/2){
                //滑到右边
                viewDragHelper.smoothSlideViewTo(mainView,dragRange,0);
                //刷新操作
                ViewCompat.postInvalidateOnAnimation(SlideMenu.this);

                //Scroller的写法
//                scroller.startScroll();
//                invalidate();

            }else {
                //滑到左边
                viewDragHelper.smoothSlideViewTo(mainView,0,0);
                //刷新操作
                ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
            }
        }
    };

    /**
     * 执行动画
     * @param fraction
     */
    private void executeAnim(float fraction) {
        //fraction:0-1
        //1.缩放mainView
        //scaleValue:1-0.6

//        float scaleValue = (0.6f-1)*fraction + 1f;
        mainView.setScaleX(floatEvaluator.evaluate(fraction,1f,0.8f));
        mainView.setScaleY(floatEvaluator.evaluate(fraction,1f,0.8f));

        //2.缩放menuView
        menuView.setScaleX(floatEvaluator.evaluate(fraction,0.4f,1f));
        menuView.setScaleY(floatEvaluator.evaluate(fraction,0.4f,1f));
        menuView.setTranslationX(floatEvaluator.evaluate(fraction,-menuWidth/2,0));

        //3.让SlideMenu的背景添加变暗的效果
        if(getBackground()!=null){
            //给Drawable添加一个颜色的遮罩效果
            getBackground().setColorFilter((Integer) argbEvaluator.evaluate(fraction,Color.BLACK,Color.TRANSPARENT),PorterDuff.Mode.SRC_OVER);
//            getBackground().setColorFilter((Integer) argbEvaluator.evaluate(fraction,Color.RED,Color.GREEN),PorterDuff.Mode.SRC_OVER);
        }
    }

    /**
     * 刷新方法->draw()->computeScroll
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
//        if(scroller.computeScrollOffset()){
//            scrollTo(scroller.getCurrX(),scroller.getCurrY());
//            invalidate();
//        }

        //判断动画有没有结束,如果为true，表示动画没有结束
        if(viewDragHelper.continueSettling(true)){
            //刷新
            ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
        }
    }

    private int clampLeft(int left) {
        if(left>dragRange){
            left=dragRange;
        }else if(left<0){
            left = 0;
        }
        return left;
    }

    OnSlideListener listener;
    public void setOnSlideLisetener(OnSlideListener listener){
        this.listener = listener;
    }

    public interface OnSlideListener{
        void onOpen();
        void onClose();
        void onDragging(float fraction);
    }

}
