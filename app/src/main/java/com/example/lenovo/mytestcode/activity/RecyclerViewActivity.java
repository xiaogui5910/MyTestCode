package com.example.lenovo.mytestcode.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.bean.Status;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {

  @BindView(R.id.rv_test1)
  RecyclerView rvTest1;

  private ArrayList<Status> list;
  private View header;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test1);
    ButterKnife.bind(this);

    list = new ArrayList<>();
    for (int i = 'A'; i < 'Z'; i++) {
      String name = "" + (char) i;
      String desc = "" + (char) i + "=desc";
      int icon = R.mipmap.ic_launcher;

      Status status = new Status(name, desc, icon);
      list.add(status);
    }
    //使RecyclerView保持固定的大小，该信息被用于自身的优化
    rvTest1.setHasFixedSize(true);

//    rvTest1.setLayoutManager(new LinearLayoutManager(this));
    rvTest1.setLayoutManager(new GridLayoutManager(this,3));
    QuickAdapter adapter = new QuickAdapter();

    //开启加载动画
    adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    //默认动画每个item只执行一次,如果想重复执行动画可以调用一下方法
    adapter.isFirstOnly(false);

    //给recyclerview设置adapter
    rvTest1.setAdapter(adapter);
    //设置空布局
    adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.emptyview, (ViewGroup) rvTest1.getParent(),false));

    //添加头布局
    header = LayoutInflater.from(this).inflate(R.layout.headview, (ViewGroup) rvTest1.getParent(), false);
    WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();
    wm.getDefaultDisplay().getMetrics(outMetrics);
    int screenWidth=outMetrics.widthPixels;
    int screenHeight=outMetrics.heightPixels;
    ViewGroup.LayoutParams layoutParams = header.getLayoutParams();
    layoutParams.width =screenWidth;
    layoutParams.height = screenHeight;
    header.setLayoutParams(layoutParams);
    Log.e("RecyclerViewActivity", "onCreate: screenWidth="+screenWidth+",screenHeight="+screenHeight );
    adapter.addHeaderView(header);

    //添加底布局
    adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.footerview, (ViewGroup) rvTest1.getParent(),false));

    //条目点击事件
    rvTest1.addOnItemTouchListener(new OnItemClickListener() {
      @Override
      public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        Toast.makeText(RecyclerViewActivity.this, "" + list.get(position).getName(), Toast.LENGTH_SHORT).show();
      }
    });

    //条目子元素点击事件
    rvTest1.addOnItemTouchListener(new OnItemChildClickListener() {
      @Override
      public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        switch (view.getId()) {
          case R.id.iv_icon:
            Toast.makeText(RecyclerViewActivity.this, "icon" + position, Toast.LENGTH_SHORT).show();
            break;
          case R.id.tv_name:
            Toast.makeText(RecyclerViewActivity.this, "name" + position, Toast.LENGTH_SHORT).show();
            break;
          case R.id.tv_desc:
            Toast.makeText(RecyclerViewActivity.this, "desc" + position, Toast.LENGTH_SHORT).show();
            break;
        }
      }
    });
  }

  public class QuickAdapter extends BaseQuickAdapter<Status,BaseViewHolder> {
    public QuickAdapter() {
      super(R.layout.item2, list);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Status item) {
      viewHolder.setText(R.id.tv_name, item.getName())
              .setText(R.id.tv_desc, item.getDesc())
              .addOnClickListener(R.id.tv_name)
              .addOnClickListener(R.id.tv_desc)
              .addOnClickListener(R.id.iv_icon)
              .setBackgroundRes(R.id.iv_icon, item.getIcon());
//      Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
  }

  @Override
  public void onBackPressed() {
//    for (int i = 0; i < rvTest1.getChildCount(); i++) {
//      View childView = rvTest1.getChildAt(i);
//      int firstVisibleItemPosition = ((LinearLayoutManager) rvTest1.getLayoutManager()).findFirstVisibleItemPosition();
//      if (firstVisibleItemPosition==0 &&childView.getTop()==0){
        super.onBackPressed();
//      }
//    }
  }
}
