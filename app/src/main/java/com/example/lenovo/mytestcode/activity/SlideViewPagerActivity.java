package com.example.lenovo.mytestcode.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.adapter.GridViewAdapter;
import com.example.lenovo.mytestcode.adapter.SlideViewPagerAdapter;
import com.example.lenovo.mytestcode.bean.Function;
import com.example.lenovo.mytestcode.utils.DensityUtils;
import com.example.lenovo.mytestcode.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideViewPagerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  private static final String TAG = "SlideViewPagerActivity";
  @BindView(R.id.viewpager)
  ViewPager viewpager;
  public final static int grid_page_num = 10;
  public final static int grid_col_num = 5;
  @BindView(R.id.ll_point)
  LinearLayout llPoint;
  @BindView(R.id.iv_show)
  Button ivShow;
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
    List<Function> showList = new ArrayList<>();
    gridList = new ArrayList<>();

    if (dataList.size() > 0) {
      dataList.clear();
    }

    for (int i = 0; i < 38; i++) {
      Function function = new Function();
      function.setName("第" + i + "个");
      dataList.add(function);
    }

//    for (int i = 0; i < 3; i++) {
//    }
    showList.addAll(dataList);

    int tempCount = showList.size() / grid_page_num;
    Log.e(TAG, "initData: tempCount=" + tempCount);
    int pageCount = showList.size() % grid_page_num == 0 ? tempCount : tempCount + 1;
    Log.e(TAG, "initData: pageCount=" + pageCount);
    for (int i = 0; i < pageCount; i++) {
      GridView gridView = new GridView(this);
      gridView.setNumColumns(grid_col_num);
      GridViewAdapter gridViewAdapter = new GridViewAdapter(showList, i);
      gridView.setOnItemClickListener(this);
      gridView.setAdapter
              (gridViewAdapter);
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
//    processBitmap();
  }


  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    int realPosition = position + viewpager.getCurrentItem() * grid_page_num;
    Function function = (Function) parent.getItemAtPosition(position);
    ToastUtil.showToast(function.getName());
    Log.e(TAG, "onItemClick: position=" + position+",realPosition="+realPosition);
    processBitmap();
  }

  private void processBitmap() {
//    ivShow.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//      @Override
//      public void onGlobalLayout() {
//        ivShow.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    ivShow.setVisibility(View.INVISIBLE);
    View rootView = viewpager.getRootView();
    rootView.setDrawingCacheEnabled(true);
    rootView.buildDrawingCache();
    Bitmap drawingCache = rootView.getDrawingCache();

    int[] location = new int[2];
    ivShow.getLocationOnScreen(location);
    int x = location[0];
    int y = location[1];
    Log.e(TAG, "onItemClick: location=" + location[0] + "," + location[1]);
//    int x = (int) ivShow.getX();
//    int y = ivShow.getTop();
    int width = ivShow.getWidth();
    int height = ivShow.getHeight();
    Log.e(TAG, "onItemClick: x,y,w,h=" + x + "," + y + "," + width + "," + height);
    Bitmap bitmap = Bitmap.createBitmap(drawingCache, x, y, width, height);

    ivShow.setBackground(new BitmapDrawable(bitmap));
    ivShow.setVisibility(View.VISIBLE);
//      }
//    });

  }
}
