package com.example.lenovo.mytestcode.widget.manager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * 描述：
 *
 * @author wx
 * @date 2018/6/1/001
 */
public class NoScrollGridLayoutManager extends GridLayoutManager {
  private boolean isScrollEnabled = true;

  public NoScrollGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                   int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public NoScrollGridLayoutManager(Context context, int spanCount) {
    super(context, spanCount);
  }

  public NoScrollGridLayoutManager(Context context, int spanCount, int orientation,
                                   boolean reverseLayout) {
    super(context, spanCount, orientation, reverseLayout);
  }

  public void setScrollEnabled(boolean flag) {
    this.isScrollEnabled = flag;
  }

  @Override
  public boolean canScrollVertically() {
    //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
    return isScrollEnabled && super.canScrollVertically();
  }
}
