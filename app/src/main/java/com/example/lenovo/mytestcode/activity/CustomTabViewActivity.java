package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.dialog.BaseDialogFragment;
import com.example.lenovo.mytestcode.dialog.ConfirmDialogFragment;
import com.example.lenovo.mytestcode.widget.HorizontalPicker;
import com.orhanobut.logger.Logger;

public class CustomTabViewActivity extends AppCompatActivity implements HorizontalPicker.OnItemClicked, HorizontalPicker.OnItemSelected {

  private static final String TAG = "CustomTabViewActivity";
  private ViewPager viewPager;
  private int[] imgs = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5, R.drawable.image_6};
  private int itemWithPadding;
  private HorizontalPicker picker;
  private int position;
  private int preSelectedPage;
  private int scrollState;


  private boolean isPullToLeft;
  private boolean isScrolling = false;
  private int lastValue = -1;
  private boolean isChange = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom_tab_view);
    initView();
    initData();
  }

  private void initView() {
    viewPager = (ViewPager) findViewById(R.id.vp_custom_tab);
    viewPager.setAdapter(new MyAdapter());
  }

  private void initData() {
    picker = (HorizontalPicker) findViewById(R.id.picker);
    String[] values = {"天气", "星空星空星空星空", "网络", "大地", "安卓", "页面"};
    picker.setValues(values);
    picker.setupWithViewPager(viewPager);
    picker.setSelectedItem(values.length-1);
    viewPager.setCurrentItem(values.length-1);
//    picker.setDefaultSelectedItem(values.length-1);


//    itemWithPadding = picker.getItemWithPadding();

    picker.setOnItemClickedListener(this);
    picker.setOnItemSelectedListener(this);
  }

  @Override
  public void onItemSelected(int index) {
    Toast.makeText(this, "Item selected", Toast.LENGTH_SHORT).show();
    viewPager.setCurrentItem(index);
    Logger.e("Item selected",index);
    Logger.d("Item selected="+index);
  }

  @Override
  public void onItemClicked(int index) {
    Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show();
    View view = View.inflate(this, R.layout.dialog_scene_finished, null);

    ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment
            .newIntance("场景命名","30分钟音乐","保存","取消",true);
    confirmDialogFragment.setDialogView(view);



    confirmDialogFragment.show(getSupportFragmentManager(),"scene_finish_dialog");
    confirmDialogFragment.setOnDialogFragmentListener(new BaseDialogFragment.OnDialogFragmentListener() {
      @Override
      public void initView(View view) {
//        Button btnDialogCancel = (Button) view.findViewById(R.id.btn_dialog_cancel);
//        btnDialogCancel.setText("我自己改的");
//        Log.e(TAG, "onItemClicked: 111111111" );
      }

      @Override
      public void dialogPositiveListener(View view) {
//        sceneInfo.setName(etSceneName.getText().toString().trim());
//        scenePlayManager.setSceneInfo(sceneInfo);
//        scenePlayManager.saveScene();
        Button btnDialogCancel = (Button) view.findViewById(R.id.btn_dialog_cancel);
        btnDialogCancel.setText("我自己改的");
        Log.e(TAG, "onItemClicked: 111111111" );
      }

      @Override
      public void dialogNegativeListener(View view) {

      }
    });


  }

  class MyAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_custom, null);
      ImageView img = (ImageView) view.findViewById(R.id.iv_adapter);
      img.setImageResource(imgs[position]);
      container.addView(view);
      return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

  }
}
