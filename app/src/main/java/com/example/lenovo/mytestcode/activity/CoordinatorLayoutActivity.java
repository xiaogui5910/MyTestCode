package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.fragment.PageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoordinatorLayoutActivity extends AppCompatActivity {

  @BindView(R.id.fab)
  FloatingActionButton fab;
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.tabs)
  TabLayout tabs;
  @BindView(R.id.appbar)
  AppBarLayout appbar;
  @BindView(R.id.viewpager)
  ViewPager viewpager;
  @BindView(R.id.activity_coordinator_layout)
  CoordinatorLayout activityCoordinatorLayout;


  private String[] titles = new String[]{"TAB1","TAB2","TAB3"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coordinator_layout);
    ButterKnife.bind(this);
    initView();
  }

  private void initView() {



    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "FAB", Snackbar.LENGTH_LONG)
                .setAction("cancel", new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                    Toast.makeText(CoordinatorLayoutActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                  }
                })
                .show();
      }
    });
    //去掉actionbar
//    getSupportActionBar().hide();

//    setSupportActionBar(toolbar);
    toolbar.setTitle("CoordinatorLayout");

    //设置viewpager数据
    MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
    viewpager.setAdapter(pagerAdapter);
//    //关联tablayout
    tabs.setupWithViewPager(viewpager);
    //设置显示模式
    tabs.setTabMode(TabLayout.MODE_FIXED);


  }

  class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    public MyFragmentPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return PageFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return titles[position];
    }
  }


}
