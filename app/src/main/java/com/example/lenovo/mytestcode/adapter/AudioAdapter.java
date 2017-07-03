package com.example.lenovo.mytestcode.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lenovo.mytestcode.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/2/7.
 */

public class AudioAdapter extends PagerAdapter {
  private static final String TAG = "AudioAdapter";
  private int[] imgs = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
          R.drawable.image_4, R.drawable.image_5, R.drawable.image_6};
  private String[] titles = {"title1", "title2", "title3", "title4", "title5", "title6"};
  private Context context;
  private View currentView;
  private List<LinearLayout> views = new ArrayList<>();
  public ImageView ivImg;

  public AudioAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return imgs.length;
  }
//
//  @Override
//  public float getBaseElevation() {
//    return 0;
//  }
//
//  @Override
//  public LinearLayout getCardViewAt(int position) {
//    return views.get(position);
//  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titles[position];
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    View view = LayoutInflater.from(container.getContext())
            .inflate(R.layout.adapter_audio, container, false);
    container.addView(view);
    ivImg = (ImageView) view.findViewById(R.id.iv_img);
//    LinearLayout llAdapterAudio = (LinearLayout) view.findViewById(R.id.ll_adapter_audio);
    ivImg.setImageResource(imgs[position]);
    return view;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public void setPrimaryItem(ViewGroup container, int position, Object object) {
    currentView = (View) object;
    super.setPrimaryItem(container, position, object);
  }

  public View getPrimaryItem() {
    return currentView;
  }
}
