package com.example.lenovo.mytestcode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.adapter.CardPagerAdapter;
import com.example.lenovo.mytestcode.adapter.ShadowTransformer;
import com.example.lenovo.mytestcode.bean.CardItem;
import com.example.lenovo.mytestcode.bean.Item;
import com.example.lenovo.mytestcode.widget.ClipViewPager;
import com.example.lenovo.mytestcode.widget.GalleryRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShareElementFor4xViewPagerActivity extends AppCompatActivity {

  public static final String VIEW_INFO_EXTRA = "view_info_extra";
  public static final String VIEW_INFO_EXTRA_BOTTOM = "view_info_extra_bottom";
  public static final String ACTION_BOTTOM_CLICK = "action_bottom_click";
  public static final String ACTION_IMG_CLICK = "action_img_click";
  private List<Item> mlist = new ArrayList<>();

  private int[] mImgs = {R.drawable.test1, R.drawable.test2, R.drawable.test3, R.drawable.test4, R.drawable.test5, R.drawable.test6, R.drawable.test7
          , R.drawable.test8, R.drawable.test9, R.drawable.test10, R.drawable.test11, R.drawable.test12, R.drawable.test13, R.drawable.test14, R.drawable.test15
          , R.drawable.test16, R.drawable.test17, R.drawable.test18, R.drawable.test19, R.drawable.test20, R.drawable.test21, R.drawable.test22, R.drawable.test23};

  private GalleryRecyclerView mGalleryRecyclerView;
  private TextView mPosition;
  private CardView cvBottomImg;
  private CardView cvBottomContain;
  private ImageView iv_bottom_floating_view_img;
  private ClipViewPager viewPager;
  private CardPagerAdapter mCardAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_share_element_for4x_view_pager);

    initData();
    initView();
  }

  /**
   * 初始化数据
   */
  private void initData() {
    for (int i = 0; i < mImgs.length; i++) {
      Item item = new Item();
      item.setImg(mImgs[i]);
      item.setName(i + "km");
      mlist.add(item);
    }

  }

  private void initView() {
    mGalleryRecyclerView = (GalleryRecyclerView) findViewById(R.id.gallery);
    //底部图片
    cvBottomImg = (CardView) findViewById(R.id.cv_bottom_img);
    mPosition = (TextView) findViewById(R.id.position);

    //底部内容边框
    cvBottomContain = (CardView) findViewById(R.id.cv_bottom_contain);
    cvBottomContain.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //点击底部跳转
        Intent intent = new Intent();
        intent.setAction(ACTION_BOTTOM_CLICK);
        intent.setClass(ShareElementFor4xViewPagerActivity.this, ShareElementFor4xSecondActivity.class);
        intent.putExtra(VIEW_INFO_EXTRA_BOTTOM, createViewInfoBundle(new View(getApplicationContext()),cvBottomImg));
        intent.putExtra("item",mlist.get(mGalleryRecyclerView.getCurrentPosition()));
        startActivity(intent);
        //去掉默认跳转
        overridePendingTransition(0, 0);
      }
    });

    iv_bottom_floating_view_img = (ImageView) findViewById(R.id.iv_bottom_floating_view_img);
    iv_bottom_floating_view_img.setImageResource(mImgs[0]);

    viewPager = (ClipViewPager) findViewById(R.id.viewPager);

    mCardAdapter = new CardPagerAdapter();
    mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
    mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
    mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
    mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));

    ShadowTransformer mCardShadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
    viewPager.setAdapter(mCardAdapter);
    viewPager.setPageTransformer(false, mCardShadowTransformer);
    viewPager.setOffscreenPageLimit(3);
  }

  private Bundle createViewInfoBundle(View view, View view1) {
    int[] screenLocation = new int[2];
    view.getLocationOnScreen(screenLocation);
    Bundle b = new Bundle();
    int left = screenLocation[0];
    int top = screenLocation[1];
    int width = view.getWidth();
    int height = view.getHeight();
    b.putInt("left", left);
    b.putInt("top", top);
    b.putInt("width", width);
    b.putInt("height", height);
    Log.e("Share11111", "createViewInfoBundle: left+top+width+height"+left+" ,"+top+" ,"+width+" ,"+height);

    int[] screenLocation1 = new int[2];
    view1.getLocationOnScreen(screenLocation1);
    int left1 = screenLocation1[0];
    int top1 = screenLocation1[1];
    int width1 = view1.getWidth();
    int height1 = view1.getHeight();
    b.putInt("left1", left1);
    b.putInt("top1", top1);
    b.putInt("width1", width1);
    b.putInt("height1", height1);
    Log.e("Share22222", "createViewInfoBundle: left+top+width+height"+left1+" ,"+top1+" ,"+width1+" ,"+height1);

    int[] screenLocation2 = new int[2];
    cvBottomContain.getLocationOnScreen(screenLocation2);
    b.putInt("contain_top",screenLocation2[1]);
    return b;
  }
}
