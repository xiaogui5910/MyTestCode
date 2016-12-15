package com.example.lenovo.mytestcode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainActivity extends AppCompatActivity {
  @Bind(R.id.rv_test)
  RecyclerView rvTest;
  private ArrayList<String> list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initData();
    initView();
  }

  private void initData() {
    list = new ArrayList<>();
    for (int i = 'A'; i < 'Z'; i++) {
      list.add(""+(char)i);
    }
  }

  private void initView() {
    rvTest.setLayoutManager(new LinearLayoutManager(this));
    rvTest.setAdapter(new MyAdapter());
    rvTest.addItemDecoration(new DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL));

  }
  class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

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
  class MyViewHolder extends RecyclerView.ViewHolder{

   TextView tvItem;

    public MyViewHolder(View itemView) {
      super(itemView);
      tvItem = (TextView) itemView.findViewById(R.id.id_num);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.mymenu,menu);
    setIconsVisible(menu,true);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.test1:
        Toast.makeText(this,"test1",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,Test1Activity.class));
        break;
      case R.id.fab:
        startActivity(new Intent(this,TabLayoutTestActivity.class));
        break;
      case R.id.action_edit:
        startActivity(new Intent(this,CoordinatorLayoutActivity.class));
        break;
      case R.id.action_nestedscrollview:
        startActivity(new Intent(this,CollapsingToolbarLayoutActivity.class));
        break;
    }
    return true;
  }

  private void setIconsVisible(Menu menu, boolean flag) {
    //判断menu是否为空
    if(menu != null) {
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
