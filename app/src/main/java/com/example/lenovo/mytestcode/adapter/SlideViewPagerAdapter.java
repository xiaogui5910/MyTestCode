package com.example.lenovo.mytestcode.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/26.
 */

public class SlideViewPagerAdapter extends PagerAdapter {
  private List<GridView> list;

  public SlideViewPagerAdapter() {
    list = new ArrayList<>();
  }

  public void setList(List<GridView> list) {
    this.list = list;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    GridView gridView = list.get(position);
    container.addView(gridView);
    return gridView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView(list.get(position));
  }
}
