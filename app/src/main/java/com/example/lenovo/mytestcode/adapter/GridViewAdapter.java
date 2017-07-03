package com.example.lenovo.mytestcode.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.activity.SlideViewPagerActivity;
import com.example.lenovo.mytestcode.bean.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/26.
 */

public class GridViewAdapter extends BaseAdapter {
  private List<Function> list;

  public GridViewAdapter(List<Function> dataList, int pageIndex) {
    list = new ArrayList<>();
    int start = pageIndex * SlideViewPagerActivity.grid_page_num;
    int end = start + SlideViewPagerActivity.grid_page_num;
    while (start < dataList.size() && start < end) {
      list.add(dataList.get(start));
      start++;
    }
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int position) {
    return list.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null){
      viewHolder = new ViewHolder();
      convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview, parent, false);
      viewHolder.img= (ImageView) convertView.findViewById(R.id.img);
      viewHolder.tv= (TextView) convertView.findViewById(R.id.tv);
      convertView.setTag(viewHolder);
    }else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    Function function = list.get(position);
    if (function!=null){
      viewHolder.img.setImageResource(R.mipmap.ic_launcher);
      viewHolder.tv.setText(function.getName());
    }

    return convertView;
  }

  private static class ViewHolder{
    private ImageView img;
    private TextView tv;
  }
}
