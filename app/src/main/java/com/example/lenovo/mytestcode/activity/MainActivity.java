package com.example.lenovo.mytestcode.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.LocationUtils;
import com.example.lenovo.mytestcode.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  @Bind(R.id.rv_test)
  RecyclerView rvTest;
  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.drawerLayout)
  DrawerLayout drawerLayout;
  private ArrayList<String> list;
  private ActionBarDrawerToggle toggle;
  private LocationManager locationManager;

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
    Log.e(TAG, "initToolbar: start---------------------------------------------");
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
    List<String> testList = new ArrayList<>();
    LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
    map.put(1, "p1");
    map.put(3, "p3");
    map.put(5, "p5");
    map.put(6, "p6");
    map.put(4, "p4");
    map.put(2, "p2");
    Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Integer, String> entry = iterator.next();
      Integer key = entry.getKey();
      String value = entry.getValue();
      testList.add(value);
      Log.e(TAG, "initData: key=" + key + ",value" + value);
    }
    Log.e(TAG, "initData: ---------------------------------------------------------");
    Collection<String> values = map.values();
    Object[] objects = values.toArray();
    for (int i = 0; i < objects.length; i++) {
      Log.e(TAG, "initData: objects=" + objects[i]);
    }
    Log.e(TAG, "initData: ---------------------------------------------------------");
    Log.e(TAG, "initData: testList=" + testList.toString());


    locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    RxPermissions rxPermissions = new RxPermissions(this);
    rxPermissions.request(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    )
            .subscribe(new Consumer<Boolean>() {
              @Override
              public void accept(Boolean granted) throws Exception {
                if (granted) { // 在android 6.0之前会默认返回true
                  // 已经获取权限
                  ToastUtil.showToast("开始下载...");
                } else {
                  // 未获取权限
                  ToastUtil.showToast("没有获取到权限，下载取消");
                }
              }
            });

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
      Log.e(TAG, "initData: 没有权限");
      return;
    }
    Criteria criterria = new Criteria();
    criterria.setAccuracy(Criteria.ACCURACY_FINE);
    criterria.setAltitudeRequired(false);
    criterria.setBearingRequired(false);
    criterria.setCostAllowed(false);
    criterria.setPowerRequirement(Criteria.ACCURACY_LOW);
    String provider = locationManager.getBestProvider(criterria, true);
    Location lastKnownLocation = locationManager.getLastKnownLocation(provider);
    Log.e(TAG, "onLocationChanged: lat=" + lastKnownLocation.getLatitude() + ", lon=" +
            lastKnownLocation.getLongitude());
//    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//            10000,          // 10-second interval.
//            10,             // 10 meters.
//            listener);
    String cnBylocation = new LocationUtils().getCNBylocation(this);
    Log.e(TAG, "initData: city=" + cnBylocation);

  }

  @Override
  protected void onStop() {
    super.onStop();
    locationManager.removeUpdates(listener);
  }

  private final LocationListener listener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
      // A new location update is received.  Do something useful with it.  In this case,
      // we're sending the update to a handler which then updates the UI with the new
      // location.
      Log.e(TAG, "onLocationChanged: =" + location.getLatitude() + ", " +
              location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
      Log.e(TAG, "onStatusChanged: 111");
    }

    @Override
    public void onProviderEnabled(String provider) {
      Log.e(TAG, "onProviderEnabled: 222");
    }

    @Override
    public void onProviderDisabled(String provider) {
      Log.e(TAG, "onProviderDisabled: 333");
    }
  };

  private void initView() {
    rvTest.setLayoutManager(new LinearLayoutManager(this));
    rvTest.setAdapter(new MyAdapter());
    rvTest.addItemDecoration(new DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL));

  }

  @OnClick({R.id.recyclerView, R.id.splashActivity, R.id.tabLayout, R.id.coordinatorLayout,
          R.id.collapsingToolbar, R.id.alarm_manager, R.id.share_element, R.id.share_element_view_pager,
          R.id.scene_custom, R.id.clip_viewPager, R.id.blur, R.id.group_recyclerview, R.id.custom_tabView, R.id.slide_menu,
          R.id.test_db, R.id.random_num, R.id.slide_viewpager, R.id.palette_imageview, R.id.downloadmanager, R.id.rxjava,
          R.id.weixin_bottom, R.id.wave_view, R.id.pie_layout, R.id.card_slider, R.id.zxing, R.id.custom_view, R.id.custom_image,
          R.id.card_view_pager})
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
      case R.id.share_element:
        goToNext(ShareElementFor4xActivity.class);
        break;
      case R.id.share_element_view_pager:
        goToNext(ShareElementFor4xViewPagerActivity.class);
        break;
      case R.id.scene_custom:
        goToNext(SceneCustomActivity.class);
        break;
      case R.id.clip_viewPager:
        goToNext(ClipViewPagerActivity.class);
        break;
      case R.id.blur:
        goToNext(BlurActivity.class);
        break;
      case R.id.group_recyclerview:
        goToNext(GroupRecyclerViewActivity.class);
        break;
      case R.id.custom_tabView:
        goToNext(CustomTabViewActivity.class);
        break;
      case R.id.slide_menu:
        goToNext(SlideMenuActivity.class);
        ToastUtil.showToast("slidemenu");
        break;
      case R.id.test_db:
        goToNext(TestDBActivity.class);
        break;
      case R.id.random_num:
        goToNext(RandomNumActivity.class);
        break;
      case R.id.slide_viewpager:
        goToNext(SlideViewPagerActivity.class);
        break;
      case R.id.palette_imageview:
        goToNext(PaletteImageViewActivity.class);
        break;
      case R.id.downloadmanager:
        goToNext(DownloadManagerActivity.class);
        break;
      case R.id.rxjava:
        goToNext(RxJavaTestActivity.class);
        break;
      case R.id.weixin_bottom:
        goToNext(Main2Activity.class);
        break;
      case R.id.wave_view:
        goToNext(WaveViewActivity.class);
        break;
      case R.id.pie_layout:
        goToNext(PieLayoutActivity.class);
        break;
      case R.id.card_slider:
        goToNext(CardSliderActivity.class);
        break;
      case R.id.zxing:
        goToNext(ZXingActivity.class);
        break;
      case R.id.custom_view:
        goToNext(CustomViewActivity.class);
        break;
      case R.id.custom_image:
        goToNext(CusImageActivity.class);
        break;
      case R.id.card_view_pager:
        goToNext(CardViewPagerActivity.class);
        break;
    }
  }

  private void goToNext(Class<?> clazz) {
    startActivity(new Intent(this, clazz));
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
