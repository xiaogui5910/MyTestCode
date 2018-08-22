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
import android.os.Environment;
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
import com.example.lenovo.mytestcode.manager.GPSLocationListener;
import com.example.lenovo.mytestcode.manager.GPSLocationManager;
import com.example.lenovo.mytestcode.manager.GPSProviderStatus;
import com.example.lenovo.mytestcode.utils.LocationUtils;
import com.example.lenovo.mytestcode.utils.NdkTestUtils;
import com.example.lenovo.mytestcode.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zy.logcat.LogCatControl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  @BindView(R.id.rv_test)
  RecyclerView rvTest;
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.drawerLayout)
  DrawerLayout drawerLayout;
  private ArrayList<String> list;
  private ActionBarDrawerToggle toggle;
  private LocationManager locationManager;
  private GPSLocationManager gpsLocationManager;

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

    String s_str = "abbabbbbcab";
    String t_str = "bbcab";

    int index = s_str.indexOf(t_str);
    Log.e(TAG, "initData: index=" + index);

//    int[] next = getNextArray(t_str);
//    Log.e(TAG, "initData: t_str=" + t_str + "\nnext=" + Arrays.toString(next));

    Log.e(TAG, "initData: s_str=" + s_str + ",t_str=" + t_str + ",kmp_index=" + kmpIndex(s_str, t_str));

    list = new ArrayList<>();
    for (int i = 'A'; i < 'Z'; i++) {
      list.add("" + (char) i + "修复后显示" + NdkTestUtils.getStringFromC());
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

//    gpsLocationManager = GPSLocationManager.getInstances(this);
//    //开启定位
//    gpsLocationManager.start(new MyListener());

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
    if (lastKnownLocation != null) {
      Log.e(TAG, "onLocationChanged: lat=" + lastKnownLocation.getLatitude() + ", lon=" +
              lastKnownLocation.getLongitude());
    }
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            10000,          // 10-second interval.
            10,             // 10 meters.
            listener);
    String cnBylocation = new LocationUtils().getCNBylocation(this);
    Log.e(TAG, "initData: city=" + cnBylocation);

    try {
      String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ijk_error.txt";
      File file = new File(path);
      FileInputStream is = new FileInputStream(file);
      int len = (int) file.length();
      Log.e(TAG, "onCreate: len=" + len);
      byte[] buffer = new byte[len];
      while ((is.read(buffer, 0, len) != -1)) {
        Log.e(TAG, "buffer=" + new String(buffer));
      }
      is.close();
    } catch (IOException ioe) {
      Log.e(TAG, "IOException: " + ioe.toString());
      ioe.printStackTrace();
    }

  }

  private int[] getNextArray(String t_str) {
    if (t_str == null) {
      return new int[255];
    }
    char[] t = t_str.toCharArray();
    int[] next = new int[t.length];

    int i, j;
    i = 0;
    j = -1;
    next[0] = -1;

    while (i < t_str.length() - 1) {
      if (j == -1 || t[i] == t[j]) {
        i++;
        j++;
        if (t[i] != t[j]) {
          next[i] = j;
        } else {
          next[i] = next[j];
        }
      } else {
        j = next[j];
      }
    }
    return next;
  }

  private int kmpIndex(String s_str, String t_str) {
    int[] next = getNextArray(t_str);
    char[] s = s_str.toCharArray();
    char[] t = t_str.toCharArray();
    int i = 0;
    int j = 0;
    while (i <= s_str.length() - 1 && j <= t_str.length() - 1) {
      if (j == -1 || s[i] == t[j]) {
        i++;
        j++;
      } else {
        j = next[j];
      }
    }
    if (j < t_str.length()) {
      return -1;
    } else {
      return i - t_str.length();
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (locationManager != null) {
      locationManager.removeUpdates(listener);
    }
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

  class MyListener implements GPSLocationListener {

    @Override
    public void UpdateLocation(Location location) {
      if (location != null) {
        Log.e(TAG, "UpdateLocation: " + "经度：" + location.getLongitude() + "\n纬度：" + location.getLatitude());
      }
    }

    @Override
    public void UpdateStatus(String provider, int status, Bundle extras) {
      if ("gps" == provider) {
        ToastUtil.showToast("定位类型：" + provider);
      }
    }

    @Override
    public void UpdateGPSProviderStatus(int gpsStatus) {
      switch (gpsStatus) {
        case GPSProviderStatus.GPS_ENABLED:
          ToastUtil.showToast("GPS开启");
          break;
        case GPSProviderStatus.GPS_DISABLED:
          ToastUtil.showToast("GPS关闭");
          break;
        case GPSProviderStatus.GPS_OUT_OF_SERVICE:
          ToastUtil.showToast("GPS不可用");
          break;
        case GPSProviderStatus.GPS_TEMPORARILY_UNAVAILABLE:
          ToastUtil.showToast("GPS暂时不可用");
          break;
        case GPSProviderStatus.GPS_AVAILABLE:
          ToastUtil.showToast("GPS可用啦");
          break;
      }
    }
  }

  private void initView() {
    rvTest.setLayoutManager(new LinearLayoutManager(this));
    MyAdapter adapter = new MyAdapter();
    rvTest.setAdapter(adapter);
    rvTest.addItemDecoration(new DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL));

  }

  @OnClick({R.id.recyclerView, R.id.splashActivity, R.id.tabLayout, R.id.coordinatorLayout,
          R.id.collapsingToolbar, R.id.alarm_manager, R.id.share_element, R.id.share_element_view_pager,
          R.id.scene_custom, R.id.clip_viewPager, R.id.blur, R.id.group_recyclerview, R.id.custom_tabView, R.id.slide_menu,
          R.id.test_db, R.id.random_num, R.id.slide_viewpager, R.id.palette_imageview, R.id.downloadmanager, R.id.rxjava,
          R.id.weixin_bottom, R.id.wave_view, R.id.pie_layout, R.id.card_slider, R.id.zxing, R.id.custom_view, R.id.custom_image,
          R.id.card_view_pager, R.id.test_annotation, R.id.level_progress, R.id.listView_checkBox,R.id.constraint_layout, R.id.scroll_layout})
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
      case R.id.test_annotation:
        goToNext(TestAnnotationActivity.class);
        break;
      case R.id.level_progress:
        goToNext(LevelProgressBarActivity.class);
        break;
      case R.id.listView_checkBox:
        goToNext(ListViewCheckBoxActivity.class);
        break;
      case R.id.constraint_layout:
        goToNext(ConstraintLayoutActivity.class);
        break;
      case R.id.scroll_layout:
        goToNext(ScrollActivity.class);
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
      final String text = list.get(position);
      holder.tvItem.setText(text);
      holder.tvItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          ToastUtil.showToast(text);
        }
      });
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
//        startActivity(new Intent(this, CoordinatorLayoutActivity.class));
        //显示dialog
        LogCatControl.getBuilder(this)
                .setTitle("自定义标题")
                .setSearchContent("自定义搜索内容")
                .setSearchTag("自定义Tag")
                .setShowGrade(3) //设置显示级别:0 所有，1 系统，2 警告,3 错误
                .show();
        break;
      case R.id.action_nestedscrollview:
//        startActivity(new Intent(this, CollapsingToolbarLayoutActivity.class));
//清除dialog
        LogCatControl.getBuilder(this).clear();
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
