package com.example.lenovo.mytestcode.activity;

import android.animation.FloatEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.Constant;
import com.example.lenovo.mytestcode.widget.SlideMenu;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideMenuActivity extends AppCompatActivity {

  @BindView(R.id.menu_listview)
  ListView menuListview;
  @BindView(R.id.iv_head)
  ImageView ivHead;
  @BindView(R.id.main_listview)
  ListView mainListview;
  @BindView(R.id.my_linearlayout)
  LinearLayout myLinearlayout;
  @BindView(R.id.slidemenu)
  SlideMenu slidemenu;

  FloatEvaluator floatEvaluator = new FloatEvaluator();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //    没有标题
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    //    全屏显示
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_slide_menu);
    ButterKnife.bind(this);

    initData();

  }

  private void initData() {
    mainListview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Constant.NAMES));
    menuListview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Constant.sCheeseStrings) {
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTextColor(Color.WHITE);
        return view;
      }
    });

    //设置滑动监听器
    slidemenu.setOnSlideLisetener(new SlideMenu.OnSlideListener() {
      @Override
      public void onOpen() {
        Toast.makeText(SlideMenuActivity.this, "onOpen", Toast.LENGTH_SHORT).show();
        menuListview.smoothScrollToPosition(new Random().nextInt(Constant.sCheeseStrings.length));
      }

      @Override
      public void onClose() {
        Toast.makeText(SlideMenuActivity.this, "onClose", Toast.LENGTH_SHORT).show();
        ViewCompat.animate(ivHead)
                .translationX(50)
                .setInterpolator(new CycleInterpolator(4))//来回反复执行的插值器
                .setDuration(1000)
                .start();
      }

      @Override
      public void onDragging(float fraction) {
        Log.e("tag", fraction + "");
//                ivHead.setAlpha(1-fraction);
        ivHead.setRotationY(floatEvaluator.evaluate(fraction, 0, 720));
      }
    });
  }
}
