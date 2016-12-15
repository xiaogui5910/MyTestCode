package com.example.lenovo.mytestcode.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;

import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
  @Bind(R.id.rv_test)
  RecyclerView rvTest;
  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.drawerLayout)
  DrawerLayout drawerLayout;
  private ArrayList<String> list;
  private ActionBarDrawerToggle toggle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initToolbar();
    initData();
    initView();
    initLeft();
  }

  private void initLeft() {

  }

  private void initToolbar() {
//    toolbar.setTitle("MyTestCode");
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
    toggle.syncState();

    drawerLayout.addDrawerListener(toggle);
  }

  private void initData() {
    list = new ArrayList<>();
    for (int i = 'A'; i < 'Z'; i++) {
      list.add("" + (char) i);
    }
  }

  private void initView() {
    rvTest.setLayoutManager(new LinearLayoutManager(this));
    rvTest.setAdapter(new MyAdapter());
    rvTest.addItemDecoration(new DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL));

  }

  @OnClick({R.id.recyclerView, R.id.splashActivity, R.id.tabLayout, R.id.coordinatorLayout, R.id.collapsingToolbar,R.id.alarm_manager})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.recyclerView:
        goToNext(RecyclerViewActivity.class);
        break;
      case R.id.splashActivity:
        goToNext(SplashActivity.class);
        break;
      case R.id.tabLayout:
        goToNext(TabLayoutTestActivity.class);
        break;
      case R.id.coordinatorLayout:
        goToNext(CoordinatorLayoutActivity.class);
        break;
      case R.id.collapsingToolbar:
        goToNext(CollapsingToolbarLayoutActivity.class);
        break;
      case R.id.alarm_manager:
        goToNext(AlarmManagerActivity.class);
        break;
    }
  }

  private void goToNext(Class<?> clazz) {
    startActivity(new Intent(this,clazz));
  }

  class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
      MyViewHolder holder = new MyViewHolder(view);
      return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      holder.tvItem.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
      return list.size();
    }
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tvItem;

    public MyViewHolder(View itemView) {
      super(itemView);
      tvItem = (TextView) itemView.findViewById(R.id.id_num);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.mymenu, menu);
    setIconsVisible(menu, true);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    toggle.onOptionsItemSelected(item);
    switch (item.getItemId()) {
      case R.id.test1:
        Toast.makeText(this, "test1", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, RecyclerViewActivity.class));
        break;
      case R.id.fab:
        startActivity(new Intent(this, TabLayoutTestActivity.class));
        break;
      case R.id.action_edit:
        startActivity(new Intent(this, CoordinatorLayoutActivity.class));
        break;
      case R.id.action_nestedscrollview:
        startActivity(new Intent(this, CollapsingToolbarLayoutActivity.class));
        break;
    }
    return true;
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    toggle.onConfigurationChanged(newConfig);
    super.onConfigurationChanged(newConfig);
  }

  private void setIconsVisible(Menu menu, boolean flag) {
    //判断menu是否为空
    if (menu != null) {
      try {
        //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
        Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
        //暴力访问该方法
        method.setAccessible(true);
        //调用该方法显示icon
        method.invoke(menu, flag);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
