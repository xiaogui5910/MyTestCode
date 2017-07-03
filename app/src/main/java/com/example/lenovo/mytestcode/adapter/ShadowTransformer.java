package com.example.lenovo.mytestcode.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;


public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

  private ViewPager mViewPager;
  private CardAdapter mAdapter;
  private float mLastOffset;

  public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
    mViewPager = viewPager;
    viewPager.addOnPageChangeListener(this);
    mAdapter = adapter;
  }

  @Override
  public void transformPage(View view, float position) {
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    int realCurrentPosition;
    int nextPosition;
    float baseElevation = mAdapter.getBaseElevation();
    float realOffset;
    boolean goingLeft = mLastOffset > positionOffset;

    //如果往后滑动，判断是否到最后一个
    if (goingLeft) {
      realCurrentPosition = position + 1;
      nextPosition = position;
      realOffset = 1 - positionOffset;
    } else {
      nextPosition = position + 1;
      realCurrentPosition = position;
      realOffset = positionOffset;
    }

    // 滑动到最后一个
    if (nextPosition > mAdapter.getCount() - 1
            || realCurrentPosition > mAdapter.getCount() - 1) {
      return;
    }

    //获取当前位置条目
    CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

    // 处理当前条目
    if (currentCard != null) {
      currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
      currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
      currentCard.setCardElevation((baseElevation + baseElevation
              * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
    }

    //获取下一要处理的条目
    CardView nextCard = mAdapter.getCardViewAt(nextPosition);

    //处理下一个要出现的条目
    if (nextCard != null) {
        nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
        nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
      nextCard.setCardElevation((baseElevation + baseElevation
              * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
    }

    mLastOffset = positionOffset;
  }

  @Override
  public void onPageSelected(int position) {

  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }
}
