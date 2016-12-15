package com.example.lenovo.mytestcode.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.fragment.MyFragment;
import com.example.lenovo.mytestcode.utils.DensityUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

  @Bind(R.id.viewpager)
  ViewPager viewpager;
  @Bind(R.id.ll_splash)
  LinearLayout llSplash;
  @Bind(R.id.btn_splash)
  Button btnSplash;
  @Bind(R.id.activity_spalsh)
  RelativeLayout activitySpalsh;

  private ArrayList<MyFragment> fragments;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_spalsh);
    ButterKnife.bind(this);

    initView();
    initData();
  }

  private void initView() {
    /*if (Build.VERSION.SDK_INT >21){
      View decorView = getWindow().getDecorView();
      int option = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(option);
      getWindow().setStatusBarColor(Color.TRANSPARENT);
    }*/
    if (Build.VERSION.SDK_INT >= 21) {
      View decorView = getWindow().getDecorView();
      int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
              | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(option);
      getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
  }

  private void initData() {
    fragments = new ArrayList<>();

    for (int i = 1; i < 7; i++) {
      MyFragment myFragment = new MyFragment();
      Bundle args = new Bundle();
      args.putInt("index", i);
      myFragment.setArguments(args);
      fragments.add(myFragment);

      ImageView iv = new ImageView(this);
      iv.setImageResource(R.drawable.selector_splash_circle);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10));
      params.leftMargin = (DensityUtils.dp2px(this, 5));
      iv.setLayoutParams(params);
      if (i == 1) {
        iv.setSelected(true);
      }
      llSplash.addView(iv);
    }

    viewpager.setOffscreenPageLimit(6);
    MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
    viewpager.setAdapter(adapter);
    viewpager.addOnPageChangeListener(this);
  }

  @OnClick(R.id.btn_splash)
  public void onClick() {
    finish();
    startActivity(new Intent(this, MainActivity.class));
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageSelected(int position) {
    for (int i = 0; i < llSplash.getChildCount(); i++) {
      ImageView childView = (ImageView) llSplash.getChildAt(i);
      childView.setSelected(i == position);
    }
    btnSplash.setVisibility(position == fragments.size() - 1 ? View.VISIBLE : View.INVISIBLE);
  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }

  class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return fragments.get(position);
    }

    @Override
    public int getCount() {
      return fragments.size();
    }
  }
}
