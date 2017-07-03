package com.example.lenovo.mytestcode.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by lenovo on 2017/2/28.
 */

public class MySection extends SectionEntity<SceneInfo> {
  private boolean isMore;
  public MySection(boolean isHeader, String header, boolean isMore) {
    super(isHeader, header);
    this.isMore = isMore;
  }

  public MySection(SceneInfo sceneinfo) {
    super(sceneinfo);
  }

  public boolean isMore() {
    return isMore;
  }

  public void setMore(boolean mroe) {
    isMore = mroe;
  }
}
