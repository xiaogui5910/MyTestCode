package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.adapter.SimpleFragmentPagerAdapter;

public class TabLayoutTestActivity extends AppCompatActivity {

  //  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_table_layout_test);
//  }
  private SimpleFragmentPagerAdapter pagerAdapter;

  private ViewPager viewPager;

  private TabLayout tabLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_table_layout_test);
    pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
    viewPager = (ViewPager) findViewById(R.id.viewpager);
    viewPager.setAdapter(pagerAdapter);
    tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabMode(TabLayout.MODE_FIXED);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        viewPager.setCurrentItem(1);
      }
    },5000);
//    for (int i = 0; i < tabLayout.getTabCount(); i++) {
//      TabLayout.Tab tab = tabLayout.getTabAt(i);
//      tab.setCustomView(pagerAdapter.getTabView(i));
//    }
  }
}
//class Find_tab_Adapter extends FragmentPagerAdapter {
//
//  private List<Fragment> list_fragment;                         //fragment列表
//  private List<String> list_Title;                              //tab名的列表
//
//
//
//  public Find_tab_Adapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
//    super(fm);
//    this.list_fragment = list_fragment;
//    this.list_Title = list_Title;
//  }
//
//  @Override
//  public Fragment getItem(int position) {
//    return list_fragment.get(position);
//  }
//
//  @Override
//  public int getCount() {
//    return list_Title.size();
//  }
//
//  //此方法用来显示tab上的名字
//  @Override
//  public CharSequence getPageTitle(int position) {
//
//    return list_Title.get(position % list_Title.size());
//  }
//}
//
//
//
///**
// * 发现页面
// */
//public class FindFragment extends Fragment {
//
//  private TabLayout tab_FindFragment_title;                            //定义TabLayout
//  private ViewPager vp_FindFragment_pager;                             //定义viewPager
//  private FragmentPagerAdapter fAdapter;                               //定义adapter
//
//  private List<Fragment> list_fragment;                                //定义要装fragment的列表
//  private List<String> list_title;                                     //tab名称列表
//
//  private Find_hotRecommendFragment hotRecommendFragment;              //热门推荐fragment
//  private Find_hotCollectionFragment hotCollectionFragment;            //热门收藏fragment
//  private Find_hotMonthFragment hotMonthFragment;                      //本月热榜fragment
//  private Find_hotToday hotToday;                                      //今日热榜fragment
//
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                           Bundle savedInstanceState) {
//    View view = inflater.inflate(R.layout.fragment_find, container, false);
//
//    initControls(view);
//
//    return view;
//  }
//
//  /**
//   * 初始化各控件
//   * @param view
//   */
//  private void initControls(View view) {
//
//    tab_FindFragment_title = (TabLayout)view.findViewById(R.id.tab_FindFragment_title);
//    vp_FindFragment_pager = (ViewPager)view.findViewById(R.id.vp_FindFragment_pager);
//
//    //初始化各fragment
//    hotRecommendFragment = new Find_hotRecommendFragment();
//    hotCollectionFragment = new Find_hotCollectionFragment();
//    hotMonthFragment = new Find_hotMonthFragment();
//    hotToday = new Find_hotToday();
//
//    //将fragment装进列表中
//    list_fragment = new ArrayList<>();
//    list_fragment.add(hotRecommendFragment);
//    list_fragment.add(hotCollectionFragment);
//    list_fragment.add(hotMonthFragment);
//    list_fragment.add(hotToday);
//
//    //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
//    list_title = new ArrayList<>();
//    list_title.add("热门推荐");
//    list_title.add("热门收藏");
//    list_title.add("本月热榜");
//    list_title.add("今日热榜");
//
//    //设置TabLayout的模式
//    tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
//    //为TabLayout添加tab名称
//    tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
//    tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
//    tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
//    tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(3)));
//
//    fAdapter = new Find_tab_Adapter(getActivity().getSupportFragmentManager(),list_fragment,list_title);
//
//    //viewpager加载adapter
//    vp_FindFragment_pager.setAdapter(fAdapter);
//    //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
//    //TabLayout加载viewpager
//    tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
//    //tab_FindFragment_title.set
//  }
//
//
//}
