package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.bean.ItemEntity;
import com.example.lenovo.mytestcode.widget.PileLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.lenovo.mytestcode.application.MyTestCodeApp.context;

public class PieLayoutActivity extends AppCompatActivity {

  private static final String TAG = "PieLayoutActivity";
  @Bind(R.id.pileLayout)
  PileLayout pileLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pie_layout);
    ButterKnife.bind(this);
    initView();
    initData();
  }

  private void initView() {

  }

  private void initData() {
    initDataList();
    pileLayout.setAdapter(new PileLayout.Adapter() {
      @Override
      public int getLayoutId() {
        return R.layout.item_layout;
      }

      @Override
      public void bindView(View view, int position) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
          viewHolder = new ViewHolder();
          viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
          viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
          view.setTag(viewHolder);
        }
        ItemEntity itemEntity = dataList.get(position);
        viewHolder.imageView.setImageResource(itemEntity.getIconId());
        viewHolder.tvTitle.setText(itemEntity.getDesc());
        Log.e(TAG, "bindView: desc=" + itemEntity.getDesc());
//        Glide.with(MainActivity.this).load(dataList.get(position).getCoverImageUrl()).into(viewHolder.imageView);
      }

      @Override
      public int getItemCount() {
        return dataList.size();
      }

      @Override
      public void displaying(int position) {
//        descriptionView.setText(dataList.get(position).getDescription() + " Since the world is so beautiful, You have to believe me, and this index is " + position);
        if (lastDisplay < 0) {
//          initSecene(position);
          lastDisplay = 0;
        } else if (lastDisplay != position) {
//          transitionSecene(position);
          lastDisplay = position;
        }
      }

      @Override
      public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
      }
    });
  }

  private void initDataList() {
    dataList = new ArrayList<>();
    for (int j = 0; j < 3; j++) {
      for (int i = 0; i < 8; i++) {
        Random r = new Random();
        int num = r.nextInt(12) + 1;
        String iconName = "pic_" + num;
        String desc = "item_" + i;
        Log.e(TAG, "initDataList: iconName=" + iconName);
        int iconId = getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        dataList.add(new ItemEntity(desc, iconId));
      }
    }
  }

  class ViewHolder {
    ImageView imageView;
    TextView tvTitle;
  }

  private int lastDisplay = -1;
  private List<ItemEntity> dataList;
}
