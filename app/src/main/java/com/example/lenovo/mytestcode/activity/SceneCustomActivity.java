package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SceneCustomActivity extends AppCompatActivity implements View.OnClickListener {

  RecyclerView rvSceneTag;
  TextView tvSceneReplace;
  Button btnSure;
  @Bind(R.id.activity_scene_custom)
  RelativeLayout activitySceneCustom;

  private ArrayList<String> list;
  private ArrayList<String> showList;
  private int index = 0;
  private SceneCustomAdapter adapter;
  private String[] tabs = {"15分钟", "30分钟", "60分钟", "90分钟"};
  private View headerView;
  private RecyclerView rvSceneList;
  private RecyclerView rvSecond;
  private SceneListAdapter secondAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scene_custom);
    ButterKnife.bind(this);
    initView();
    initData();
  }

  private void initView() {
    rvSceneList = (RecyclerView) findViewById(R.id.rv_scene_list);

    headerView = View.inflate(this, R.layout.header_custom_scene_list, null);
    rvSceneTag= (RecyclerView) headerView.findViewById(R.id.rv_scene_tag);
    rvSecond = (RecyclerView) headerView.findViewById(R.id.rv_second);
    tvSceneReplace= (TextView) headerView.findViewById(R.id.tv_scene_replace);
    btnSure= (Button) headerView.findViewById(R.id.btn_sure);
    tvSceneReplace.setOnClickListener(this);
    btnSure.setOnClickListener(this);

  }

  private void initData() {

    list = new ArrayList<>();
    showList = new ArrayList<>();
    //初始化所有tab集合
    for (int i = 0; i < 90; i++) {
      list.add("标签" + i);
    }
    //场景列表recyclerview
    rvSceneList.setLayoutManager(new LinearLayoutManager(this));
    SceneListAdapter listAdapter = new SceneListAdapter(R.layout.item,list);
    rvSceneList.setAdapter(listAdapter);
    listAdapter.addHeaderView(headerView);

    //初始化显示tag集合
    initShowTagList();

    rvSceneTag.setHasFixedSize(true);
    rvSceneTag.setLayoutManager(new GridLayoutManager(this, 3));
    adapter = new SceneCustomAdapter(R.layout.item_scene_tag_rv, showList);
    rvSceneTag.setAdapter(adapter);

    rvSceneTag.addOnItemTouchListener(new OnItemClickListener() {
      @Override
      public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        if (view.isSelected()) {
          view.setSelected(false);
        } else {
          view.setSelected(true);
        }
        ToastUtil.showToast(list.get(position));
      }
    });

    //头布局第二个recyclerview
    rvSecond.setLayoutManager(new LinearLayoutManager(this));
    secondAdapter = new SceneListAdapter(R.layout.item,showList);
    rvSecond.setAdapter(secondAdapter);
    rvSecond.addOnItemTouchListener(new OnItemClickListener() {
      @Override
      public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        ToastUtil.showToast(list.get(position)+" second");
      }
    });

  }

  private void initShowTagList() {
    showList.clear();
    for (int i = index; i < index + 9; i++) {
      showList.add(list.get(i));
    }
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_scene_replace:
        index += 9;
        if (index == list.size()) {
          index = 0;
        }
        //重新初始化tag集合数据
        initShowTagList();
        adapter.notifyDataSetChanged();
        secondAdapter.notifyDataSetChanged();
        break;
      case R.id.btn_sure:
        for (int i = 0; i < rvSceneTag.getChildCount(); i++) {
          View childView = rvSceneTag.getChildAt(i);
          if (childView.isSelected()) {

          }
        }

        break;
    }
  }



  public class SceneCustomAdapter extends BaseQuickAdapter<String> {

    public SceneCustomAdapter(int layoutResId, List<String> data) {
      super(layoutResId, data);
    }

    public SceneCustomAdapter(List<String> data) {
      super(data);
    }

    public SceneCustomAdapter(View contentView, List<String> data) {
      super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
      baseViewHolder.setText(R.id.tv_tag, s);
    }
  }

  public class SceneListAdapter extends BaseQuickAdapter<String>{

    public SceneListAdapter(int layoutResId, List<String> data) {
      super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
      baseViewHolder.setText(R.id.id_num,s);
    }
  }
}
