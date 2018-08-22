package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.bean.Item;
import com.example.lenovo.mytestcode.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardViewPagerActivity extends AppCompatActivity {

  private static final String TAG = "CardViewPagerActivity";
  @BindView(R.id.viewPager)
  ViewPager viewPager;

  private static final int OUTER_COUNT = 327;
  private static final int INNER_COUNT = 10;
  private static final int PAGE_COUNT = 3;
  private int currIndex;
  private List<Item> dataList;
  private List<Item> showList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_view_pager);
    ButterKnife.bind(this);
    initData();
  }

  private void initData() {
    dataList = new ArrayList<>();
    showList = new ArrayList<>();
    for (int i = 1; i <= 11; i++) {
      Item item = new Item();
      item.setName("item" + i);
      int iconResId = getResources().getIdentifier("pic_" + i, "drawable", getPackageName());
      item.setImg(iconResId);
      dataList.add(item);
    }

    for (int i = 0; i < OUTER_COUNT; i++) {
      showList.addAll(dataList);
    }

    CardViewPagerAdapter adapter = new CardViewPagerAdapter();
    viewPager.setAdapter(adapter);

    int a = OUTER_COUNT / 4;
    int b = a / 2;
    int c = b * 4;
    viewPager.setCurrentItem(c);
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        Log.e(TAG, "onPageSelected: currPosition=" + position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }

  private class CardViewPagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      int size = showList.size();
      int count = size / PAGE_COUNT;
      if (size % PAGE_COUNT != 0) {
        count += 1;
      }
      return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_card_view_pager, null);


      RelativeLayout rlCardItem = (RelativeLayout) view.findViewById(R.id.rl_card_item);
      ImageView ivImage = (ImageView) view.findViewById(R.id.iv_image);
      TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
      TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);

      RelativeLayout rlCardItem1 = (RelativeLayout) view.findViewById(R.id.rl_card_item1);
      ImageView ivImage1 = (ImageView) view.findViewById(R.id.iv_image1);
      TextView tvTitle1 = (TextView) view.findViewById(R.id.tv_title1);
      TextView tvDesc1 = (TextView) view.findViewById(R.id.tv_desc1);

      RelativeLayout rlCardItem2 = (RelativeLayout) view.findViewById(R.id.rl_card_item2);
      ImageView ivImage2 = (ImageView) view.findViewById(R.id.iv_image2);
      TextView tvTitle2 = (TextView) view.findViewById(R.id.tv_title2);
      TextView tvDesc2 = (TextView) view.findViewById(R.id.tv_desc2);


      int showPosition = position * PAGE_COUNT;
      int size = dataList.size();
      int dataPosition = showPosition % size;

      int remainder = size % PAGE_COUNT;
      int quotient = size / PAGE_COUNT;
      Log.e(TAG, "instantiateItem: remainder=" + remainder + ",quotient=" + quotient + ",dataPosition=" + dataPosition + ",--------position" + position);
      boolean isShow;
      if (remainder == 0) {
        isShow = true;


      } else {
        quotient += 1;
        isShow = false;
      }
      Item item = new Item();
      Item entity1 = new Item();
      Item entity2 = new Item();
      boolean isEndData = false;
      int currIndex=0;
      //完成所有数据初始化的那个页面
      int tempRemainder = (position + 1) % quotient;
      switch (tempRemainder) {
        case 0:
          currIndex = 9;
          break;
        case 1:
          currIndex = 0;
          break;
        case 2:
          currIndex = 3;
          break;
        case 3:
          currIndex = 6;
          break;
      }
//      //数据初始化
//      item = dataList.get(currIndex);
//      tvDesc.setText(item.getName());
//      tvTitle.setText(item.getName());
//      ivImage.setImageResource(item.getImg());
//      currIndex++;
//      if (tempRemainder==0){
//        if (remainder == 1) {
//          //数据初始化
//          item = dataList.get(currIndex);
//          tvDesc.setText(item.getName());
//          tvTitle.setText(item.getName());
//          ivImage.setImageResource(item.getImg());
//          currIndex++;
//        } else if (remainder == 2) {
//          //数据初始化
//          item = dataList.get(currIndex);
//          tvDesc.setText(item.getName());
//          tvTitle.setText(item.getName());
//          ivImage.setImageResource(item.getImg());
//          currIndex++;
//
//          entity1 = dataList.get(currIndex);
//
//          tvDesc1.setText(entity1.getName());
//          tvTitle1.setText(entity1.getName());
//          ivImage1.setImageResource(entity1.getImg());
//          currIndex++;
//        }else {
//          //数据初始化
//          item = dataList.get(currIndex);
//          tvDesc.setText(item.getName());
//          tvTitle.setText(item.getName());
//          ivImage.setImageResource(item.getImg());
//          currIndex++;
//
//          entity1 = dataList.get(currIndex);
//
//          tvDesc1.setText(entity1.getName());
//          tvTitle1.setText(entity1.getName());
//          ivImage1.setImageResource(entity1.getImg());
//          currIndex++;
//
//          entity2 = dataList.get(currIndex);
//          tvDesc2.setText(entity2.getName());
//          tvTitle2.setText(entity2.getName());
//          ivImage2.setImageResource(entity2.getImg());
//          currIndex++;
//        }
//      }

      if (tempRemainder == 0) {
        if (!isShow) {
          Log.e(TAG, "instantiateItem: position=" + position);
          Log.e(TAG, "instantiateItem:remainder= " + remainder);
          if (remainder == 1) {
            isEndData = true;
            rlCardItem1.setVisibility(View.INVISIBLE);
            rlCardItem2.setVisibility(View.INVISIBLE);

            //数据初始化
            item = dataList.get(currIndex);
            tvDesc.setText(item.getName());
            tvTitle.setText(item.getName());
            ivImage.setImageResource(item.getImg());
            currIndex++;
          } else if (remainder == 2) {
            isEndData = true;
            rlCardItem2.setVisibility(View.INVISIBLE);
            Log.e(TAG, "instantiateItem: dataPosition=" + dataPosition);

            //数据初始化
            item = dataList.get(currIndex);
            tvDesc.setText(item.getName());
            tvTitle.setText(item.getName());
            ivImage.setImageResource(item.getImg());
            currIndex++;

            entity1 = dataList.get(currIndex);

            tvDesc1.setText(entity1.getName());
            tvTitle1.setText(entity1.getName());
            ivImage1.setImageResource(entity1.getImg());
            currIndex++;

          } else {
            isEndData = false;
            rlCardItem1.setVisibility(View.VISIBLE);
            rlCardItem2.setVisibility(View.VISIBLE);

            //数据初始化
            item = dataList.get(currIndex);
            tvDesc.setText(item.getName());
            tvTitle.setText(item.getName());
            ivImage.setImageResource(item.getImg());
            currIndex++;

            entity1 = dataList.get(currIndex);

            tvDesc1.setText(entity1.getName());
            tvTitle1.setText(entity1.getName());
            ivImage1.setImageResource(entity1.getImg());
            currIndex++;

            entity2 = dataList.get(currIndex);
            tvDesc2.setText(entity2.getName());
            tvTitle2.setText(entity2.getName());
            ivImage2.setImageResource(entity2.getImg());
            currIndex++;

          }
        }
      } else {

        item = dataList.get(currIndex);
        tvDesc.setText(item.getName());
        tvTitle.setText(item.getName());
        ivImage.setImageResource(item.getImg());
        currIndex++;

        entity1 = dataList.get(currIndex);

        tvDesc1.setText(entity1.getName());
        tvTitle1.setText(entity1.getName());
        ivImage1.setImageResource(entity1.getImg());
        currIndex++;

        entity2 = dataList.get(currIndex);
        tvDesc2.setText(entity2.getName());
        tvTitle2.setText(entity2.getName());
        ivImage2.setImageResource(entity2.getImg());

      }

      final Item finalItem = item;
      rlCardItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          processItemClick(finalItem);
        }
      });
      final Item finalEntity = entity1;
      rlCardItem1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          processItemClick(finalEntity);
        }
      });
      final Item finalEntity1 = entity2;
      rlCardItem2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          processItemClick(finalEntity1);
        }
      });

      container.addView(view);

      return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
      return 0.883f;
    }
  }

  private void processItemClick(Item item) {
    ToastUtil.showToast(item.getName());
  }
}
