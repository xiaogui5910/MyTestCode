package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.bean.Item;
import com.example.lenovo.mytestcode.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewCheckBoxActivity extends AppCompatActivity {

  @BindView(R.id.listView)
  ListView listView;
  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;
  private List<Item> dataList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_view_check_box);
    ButterKnife.bind(this);
    initData();
  }

  private void initData() {
    dataList = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
      Item item = new Item();
      item.setPosition(i);
      item.setName("第" + i + "个位置");
      dataList.add(item);
    }
    final MyListViewAdapter adapter = new MyListViewAdapter();
    listView.setAdapter(adapter);
//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Item item = (Item)parent.getItemAtPosition(position);
//        item.setSelected(!item.isSelected());
//        ToastUtil.showToast(item.getName());
//        adapter.notifyDataSetChanged();
//      }
//    });

    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    final MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(R.layout.item_list_view_check_box, dataList);
    recyclerView.setAdapter(myRecyclerViewAdapter);
    recyclerView.addOnItemTouchListener(new OnItemClickListener() {
      @Override
      public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        Item item = (Item) baseQuickAdapter.getItem(position);
        item.setSelected(!item.isSelected());
        ToastUtil.showToast(item.getName());
        myRecyclerViewAdapter.notifyDataSetChanged();
      }
    });
  }

  class MyListViewAdapter extends BaseAdapter {

    @Override
    public int getCount() {
      return dataList.size();
    }

    @Override
    public Object getItem(int position) {
      return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      MyListViewHolder viewHolder = null;
      if (convertView == null) {
        viewHolder = new MyListViewHolder();
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_check_box, null);
        viewHolder.tvNameList = (TextView) convertView.findViewById(R.id.tv_name_list);
        viewHolder.cbList = (CheckBox) convertView.findViewById(R.id.cb_list);
        convertView.setTag(viewHolder);
      } else {
        viewHolder = (MyListViewHolder) convertView.getTag();
      }
      final Item item = dataList.get(position);
      viewHolder.tvNameList.setText(item.getName());
//      viewHolder.cbList.setChecked(item.isSelected());
      viewHolder.cbList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          item.setSelected(isChecked);

        }
      });
      viewHolder.cbList.setChecked(item.isSelected());
      return convertView;
    }

    class MyListViewHolder {
      TextView tvNameList;
      CheckBox cbList;
    }
  }

  class MyRecyclerViewAdapter extends BaseQuickAdapter<Item,BaseViewHolder> {

    public MyRecyclerViewAdapter(int layoutResId, List<Item> data) {
      super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Item s) {
      baseViewHolder.setText(R.id.tv_name_list, s.getName());
      CheckBox checkBox = baseViewHolder.getView(R.id.cb_list);
      checkBox.setChecked(s.isSelected());
    }
  }

}
