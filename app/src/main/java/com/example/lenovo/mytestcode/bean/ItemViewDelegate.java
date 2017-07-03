package com.example.lenovo.mytestcode.bean;

import com.example.lenovo.mytestcode.viewholder.ViewHolder;

/**
 * Created by lenovo on 2016/12/22.
 */
public interface ItemViewDelegate<T>
{

  int getItemViewLayoutId();

  boolean isForViewType(T item, int position);

  void convert(ViewHolder holder, T t, int position);

}
