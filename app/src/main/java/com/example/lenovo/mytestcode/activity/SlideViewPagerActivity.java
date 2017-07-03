package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.adapter.GridViewAdapter;
import com.example.lenovo.mytestcode.adapter.SlideViewPagerAdapter;
import com.example.lenovo.mytestcode.bean.Function;
import com.example.lenovo.mytestcode.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SlideViewPagerActivity extends AppCompatActivity {

  private static final String TAG ="SlideViewPagerActivity" ;
  @Bind(R.id.viewpager)
  ViewPager viewpager;
  public final static int grid_page_num = 10;
  public final static int grid_col_num = 5;
  @Bind(R.id.ll_point)
  LinearLayout llPoint;
  private List<GridView> gridList;
  private int lastPosition;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_slide_view_pager);
    ButterKnife.bind(this);
    initData();
    initView();
  }

  private void initView() {

  }

  private void initData() {
    SlideViewPagerAdapter adapter = new SlideViewPagerAdapter();

    List<Function> dataList = new ArrayList<>();
    gridList = new ArrayList<>();

    if (dataList.size() > 0) {
      dataList.clear();
    }

    for (int i = 0; i < 60; i++) {
      Function function = new Function();
      function.setName("第" + i + "个");
      dataList.add(function);
    }

    int tempCount = dataList.size() / grid_page_num;
    Log.e(TAG, "initData: tempCount="+tempCount );
    int pageCount =  dataList.size() % grid_page_num == 0 ? tempCount : tempCount + 1;
    Log.e(TAG, "initData: pageCount="+pageCount );
    for (int i = 0; i < pageCount; i++) {
      GridView gridView = new GridView(this);
      gridView.setNumColumns(grid_col_num);
      GridViewAdapter gridViewAdapter = new GridViewAdapter(dataList, i);
      gridView.setAdapter(gridViewAdapter);
      gridList.add(gridView);

      ImageView iv = new ImageView(this);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      params.weight = DensityUtils.dp2px(this, 10);
      params.width = DensityUtils.dp2px(this, 10);
      params.leftMargin = DensityUtils.dp2px(this, 5);
      iv.setLayoutParams(params);
      iv.setImageResource(R.drawable.selector_point);

      llPoint.addView(iv);
    }
    adapter.setList(gridList);
    viewpager.setAdapter(adapter);
    View childView = llPoint.getChildAt(0);
    childView.setSelected(true);

    viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        View childView = llPoint.getChildAt(position);
        View lastChildView = llPoint.getChildAt(lastPosition);
        lastChildView.setSelected(false);
        childView.setSelected(true);
        lastPosition = position;
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }


}
