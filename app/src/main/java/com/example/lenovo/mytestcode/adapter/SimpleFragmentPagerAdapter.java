package com.example.lenovo.mytestcode.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.fragment.PageFragment;

/**
 * Created by lenovo on 2016/11/16.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

  final int PAGE_COUNT = 3;
  private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};
  private Context context;

  private int[] imageResId = {
          R.drawable.one,
          R.drawable.two,
          R.drawable.three
  };

  public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override
  public Fragment getItem(int position) {
    return PageFragment.newInstance(position + 1);
  }

  @Override
  public int getCount() {
    return PAGE_COUNT;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    // Generate title based on item position
    Drawable image = context.getResources().getDrawable(imageResId[position]);
    image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
    // Replace blank spaces with image icon
    SpannableString sb = new SpannableString("   " + tabTitles[position]);
    ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
    sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return sb;

//    return tabTitles[position];
//    return null;
  }
  public View getTabView(int position){
    View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
    TextView tv= (TextView) view.findViewById(R.id.textView);
    tv.setText(tabTitles[position]);
    ImageView img = (ImageView) view.findViewById(R.id.imageView);
    img.setImageResource(imageResId[position]);
    return view;
  }
}
