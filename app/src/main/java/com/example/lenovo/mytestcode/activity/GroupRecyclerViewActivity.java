package com.example.lenovo.mytestcode.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.bean.MySection;
import com.example.lenovo.mytestcode.bean.SceneInfo;
import com.example.lenovo.mytestcode.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupRecyclerViewActivity extends AppCompatActivity {

  @BindView(R.id.rv_group)
  RecyclerView rvGroup;
  @BindView(R.id.activity_group_recycler_view)
  RelativeLayout activityGroupRecyclerView;
  private ArrayList<SceneInfo> customSceneList;
  private ArrayList<SceneInfo> showMySceneList;
  private ArrayList<SceneInfo> scenes;
  private ArrayList<MySection> list;
  private MySection moreSection;
  private boolean isShowingAll;
  private GroupAdapter groupAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_group_recycler_view);
    ButterKnife.bind(this);
    initView();
    initData();
  }

  private void initView() {

  }

  private void initData() {
    list = new ArrayList<>();
    customSceneList = new ArrayList<>();
    showMySceneList = new ArrayList<>();
    scenes = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      SceneInfo sceneInfo = new SceneInfo();
      sceneInfo.setName("my" + i);
      customSceneList.add(sceneInfo);
    }
    for (int i = 0; i < 15; i++) {
      SceneInfo sceneInfo = new SceneInfo();
      sceneInfo.setName("recommend" + i);
      scenes.add(sceneInfo);
    }

//    initCustomScene();
    initShowMySceneList();
    initRecommendScene();


    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    groupAdapter = new GroupAdapter(R.layout.cell_channel, R.layout.item_group_recyecler_view, list);
    rvGroup.setLayoutManager(linearLayoutManager);
    rvGroup.setAdapter(groupAdapter);

    rvGroup.addOnItemTouchListener(new OnItemClickListener() {
      @Override
      public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        MySection mySection = (MySection) baseQuickAdapter.getItem(position);
        if (mySection.isHeader) {
//          ToastUtil.showToast(mySection.header+position);
        } else {
          ToastUtil.showToast(mySection.t.getName() + position);
        }
      }

      @Override
      public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.tv_read_more);
        toggleShowAllScene(textView);
      }
    });

  }

  private void initRecommendScene() {
    //推荐场景不为空
    if (scenes != null && scenes.size() > 0) {
      //添加推荐场景的分类标签名称
      MySection mySection = new MySection(true, "Recommend Scene", false);
      list.add(mySection);
    }
    //添加推荐场景列表
    for (int i = 0; i < scenes.size(); i++) {
      SceneInfo sceneInfo = scenes.get(i);
      MySection mySection = new MySection(sceneInfo);
      list.add(mySection);
    }
  }

  private void initCustomScene() {
    //有我的场景列表情况下
    if (customSceneList != null && customSceneList.size() > 0) {
      //添加我的场景的分类标签名称
      MySection mySection = new MySection(true, "My Scene", false);
      list.add(mySection);
    }

    //我的场景个数超过4个之后添加更多标签
    if (customSceneList.size() > 4) {
      //添加我的场景的分类标签名称
      moreSection = new MySection(true, "Read More", true);
      isShowingAll=false;
    }
    //添加我的场景列表
    for (int i = 0; i < customSceneList.size(); i++) {
      if (i <= 4) {
        SceneInfo sceneInfo = customSceneList.get(i);
        MySection mySection = new MySection(sceneInfo);
        //第四个我的场景后面添加
        if (i == 4) {
          list.add(moreSection);
        } else {
          list.add(mySection);
        }
      }
    }

  }

  class GroupAdapter extends BaseSectionQuickAdapter<MySection,BaseViewHolder> {

    public GroupAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
      super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, MySection mySection) {
      baseViewHolder.setText(R.id.tv_scene_tag, mySection.header)
              .setText(R.id.tv_read_more,isShowingAll?"收起全部":"展开全部")
              .setVisible(R.id.tv_read_more, mySection.isMore())
              .setVisible(R.id.tv_scene_tag, !mySection.isMore())
              .addOnClickListener(R.id.tv_read_more);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MySection mySection) {
      baseViewHolder.setText(R.id.nameTextView, mySection.t.getName());
    }
  }

  /**
   * 显示全部我的场景开关
   */
  private void toggleShowAllScene(View view) {
    isShowingAll = !isShowingAll;
    if (!isShowingAll) {
      ToastUtil.showToast("show part scene");
      rvGroup.scrollToPosition(0);
    } else {
//      showMySceneList.clear();
//      showMySceneList.addAll(customSceneList);
      ToastUtil.showToast("show all scene");
    }
    initShowMySceneList();
    initRecommendScene();
    groupAdapter.notifyDataSetChanged();
//    updateFooterUi(view);

  }

  /**
   * 初始化要显示我的场景数据
   */
  private void initShowMySceneList() {
    if (customSceneList == null || customSceneList.size() == 0) {
      return;
    }
    showMySceneList.clear();
    list.clear();

    //添加我的场景的分类标签名称
    MySection myTagSection = new MySection(true, "My Scene", false);
    list.add(myTagSection);

    //添加我的场景的分类标签名称
    moreSection = new MySection(true, "Read More", true);

    //我的场景个数超过4个之后添加更多标签
    if (!isShowingAll) {
      //添加我的场景列表
      for (int i = 0; i < 4; i++) {
        showMySceneList.add(customSceneList.get(i));
        SceneInfo sceneInfo = customSceneList.get(i);
        MySection mySection = new MySection(sceneInfo);
        //第四个我的场景后面添加
        if (i == 3) {
          list.add(mySection);
          list.add(moreSection);
        } else {
          list.add(mySection);
        }
      }
    } else {
//      showMySceneList.addAll(customSceneList);
      //添加我的场景列表
      for (int i = 0; i < customSceneList.size(); i++) {
        SceneInfo sceneInfo = customSceneList.get(i);
        MySection mySection = new MySection(sceneInfo);
        list.add(mySection);
      }
      list.add(moreSection);
    }
  }

  /**
   * 更新底部文字和图标显示
   */
  private void updateFooterUi(View view) {
    TextView tvMore = (TextView) view;
    tvMore.setText(isShowingAll ? "收起全部" : "展开全部");
    int resId = isShowingAll ? R.drawable.icon_arrow_up : R.drawable.icon_arrow_down;
    Drawable drawable = ContextCompat.getDrawable(this, resId);
    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    tvMore.setCompoundDrawables(null, null, drawable, null);
  }

}
