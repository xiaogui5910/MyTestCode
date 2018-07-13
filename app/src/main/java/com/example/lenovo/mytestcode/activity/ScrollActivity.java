package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.ToastUtil;
import com.example.lenovo.mytestcode.widget.helper.PagingScrollHelper;
import com.example.lenovo.mytestcode.widget.manager.NoScrollGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.lenovo.mytestcode.application.MyTestCodeApp.context;

public class ScrollActivity extends AppCompatActivity implements PagingScrollHelper.onPageChangeListener {

  @Bind(R.id.iv_title_back)
  ImageView ivTitleBack;
  @Bind(R.id.tv_top_title)
  TextView tvTopTitle;
  @Bind(R.id.tv_second_title)
  TextView tvSecondTitle;
  @Bind(R.id.tv_desc)
  TextView tvDesc;
  @Bind(R.id.iv_pic)
  ImageView ivPic;
  @Bind(R.id.tl_date)
  TabLayout tlDate;
//  @Bind(R.id.vp_list)
//  ViewPager vpList;
  @Bind(R.id.iv_up)
  ImageView ivUp;
  @Bind(R.id.tv_index)
  TextView tvIndex;
  @Bind(R.id.iv_down)
  ImageView ivDown;
  @Bind(R.id.rv_content_list)
  RecyclerView rvContentList;

//  PagingScrollHelper scrollHelper = new PagingScrollHelper();
  private LinearLayoutManager vLinearLayoutManager;
  private List<String> tabs;
  private List<List<String>> dataList;
  private static int item_grid_num = 4;//每一页中GridView中item的数量
  private static int number_columns = 1;//gridview一行展示的数目
  private MyAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scroll);
    ButterKnife.bind(this);
    initView();
    initData();
  }

  private void initView() {
    tlDate.addOnTabSelectedListener(tabListener);
//    vpList.addOnPageChangeListener(pageListener);
  }

  private void initData() {
    tabs = new ArrayList<>();
    dataList = new ArrayList<>();


    for (int i = 0; i < 4; i++) {
      tabs.add("标签" + i);
      tlDate.addTab(tlDate.newTab().setText("标签" + i));
      List<String> list = new ArrayList<>();
      for (int j = 0; j < 20; j++) {
        list.add("标签" + i + "的条目" + j);
      }
      dataList.add(list);
    }


    List<String> data = dataList.get(0);

//    scrollHelper.setUpRecycleView(rvContentList);
//    scrollHelper.setOnPageChangeListener(this);

    vLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//    adapter = new MyRecyclerViewAdapter(R.layout.item_list, data);
    adapter = new MyAdapter();
    rvContentList.setLayoutManager(vLinearLayoutManager);
    rvContentList.setAdapter(adapter);

//    rvContentList.setHorizontalScrollBarEnabled(true);
//    //获取总页数,采用这种方法才能获得正确的页数。否则会因为RecyclerView.State 缓存问题，页数不正确。
//    rvContentList.post(new Runnable() {
//      @Override
//      public void run() {
////        tvTopTitle.setText("共" + scrollHelper.getPageCount() + "页");
//      }
//    });
    //默认显示第一个
//    createPageAdapter(data);

  }

  private void createPageAdapter(List<String> data) {
    final List<RecyclerView> gridList = new ArrayList<>();
    if (data == null) {//dataList: GridView所用数据
      data = new ArrayList<>();
    }
    if (gridList.size() > 0) {
      return;
    }
    int size = data.size();

    final int pageSize =
            size % item_grid_num == 0 ? size / item_grid_num : size / item_grid_num + 1;

    for (int i = 0; i < pageSize; i++) {

      final RecyclerView recyclerView = new RecyclerView(context);
      final List<String> datas = new ArrayList<>();
      //start end分别代表要显示的数组在总数据List中的开始和结束位置
      int start = i * item_grid_num;
      int end = start + item_grid_num;
      while ((start < data.size()) && (start < end)) {
        datas.add(data.get(start));
        start++;
      }

      final NoScrollGridLayoutManager layoutManager =
              new NoScrollGridLayoutManager(context, number_columns, LinearLayoutManager.VERTICAL,
                      false);
      layoutManager.setScrollEnabled(true);

      MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.item_list, datas);
      // 大文件处理

      recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
      RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
      if (animator instanceof SimpleItemAnimator) {
        ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
      }
      adapter.setNewData(datas);

      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setAdapter(adapter);

      recyclerView.addOnItemTouchListener(new OnItemClickListener() {
        @Override
        public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
          ToastUtil.showToast((String) baseQuickAdapter.getItem(i));
        }
      });
      gridList.add(recyclerView);
    }

//    CategoryViewPagerAdapter pagerAdapter = new CategoryViewPagerAdapter();
//    pagerAdapter.add(gridList);
//    vpList.setAdapter(pagerAdapter);
//    vpList.setCurrentItem(0);
//    tvIndex.setText("1");
  }

  private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
      ToastUtil.showToast((String) tab.getText());
      if (dataList == null || dataList.size() == 0) {
        return;
      }
//      createPageAdapter(dataList.get(tab.getPosition()));
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
  };

  private ViewPager.OnPageChangeListener pageListener = new ViewPager.SimpleOnPageChangeListener() {

    @Override
    public void onPageSelected(int position) {
//      tvIndex.setText((position + 1) + "");
    }
  };

  @OnClick({R.id.iv_title_back, R.id.iv_up, R.id.iv_down})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.iv_title_back:
        finish();
        break;
      case R.id.iv_up:
//        vpList.setCurrentItem(vpList.getCurrentItem() - 1);
        break;
      case R.id.iv_down:
//        vpList.setCurrentItem(vpList.getCurrentItem() + 1);
        break;
    }
  }

  @Override
  public void onPageChange(int index) {
    tvIndex.setText( (index + 1) + "");
  }

//  class MyViewPagerAdapter extends PagerAdapter {
//
//    @Override
//    public int getCount() {
//      return tabs.size();
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//      return tabs.get(position);
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//      return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
////      View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_scroll, null);
////      RecyclerView rvItemList = (RecyclerView) view.findViewById(R.id.rv_item_list);
////      List<String> list = dataList.get(position);
////
////      rvItemList.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));
////      MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.item_list, list);
////      rvItemList.setAdapter(adapter);
//
//      container.addView(view);
//      return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//      container.removeView((View) object);
//    }
//  }

  class CategoryViewPagerAdapter extends PagerAdapter {

    private List<RecyclerView> gridList;//每页GridView数据

    public CategoryViewPagerAdapter() {
      gridList = new ArrayList<>();
    }

    public void add(List<RecyclerView> datas) {
      if (gridList.size() > 0) {
        gridList.clear();
      }
      gridList.addAll(datas);
      notifyDataSetChanged();
    }

    @Override
    public int getCount() {
      return gridList.size();
    }

    @Override
    public int getItemPosition(Object object) {
      return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      container.addView(gridList.get(position));
      return gridList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }
  }

  class MyRecyclerViewAdapter extends BaseQuickAdapter<String> {

    public MyRecyclerViewAdapter(int layoutResId, List<String> data) {
      super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
      baseViewHolder.setText(R.id.tv_item, s);
    }
  }

  class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private  List<String> data = new ArrayList<>();
    private  int data_name = 0;
    private Random random=new Random();
    public MyAdapter(){
      setData();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View view = inflater.inflate(R.layout.layout_item, parent, false);
      return new MyViewHolder(view);
    }


    private void setData(){
      int size=random.nextInt(70);
      for (int i = 1; i <= size; i++) {
        data.add(data_name + "-" + i + "");
      }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      final String title = data.get(position);
      holder.tv_title.setText(title);
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Toast.makeText(v.getContext(), "item" + title + " 被点击了", Toast.LENGTH_SHORT).show();
        }
      });
    }

    @Override
    public int getItemCount() {
      return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
      TextView tv_title;

      public MyViewHolder(View itemView) {
        super(itemView);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
      }
    }

    public void updateData() {
      data_name++;
      data.clear();
      setData();

    }
  }

}
