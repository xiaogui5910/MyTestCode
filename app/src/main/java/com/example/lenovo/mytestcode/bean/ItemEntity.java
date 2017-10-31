package com.example.lenovo.mytestcode.bean;

/**
 * Created by lenovo on 2017/7/17.
 */

public class ItemEntity {
  String desc;
  int iconId;

  public ItemEntity(String desc, int iconId) {
    this.desc = desc;
    this.iconId = iconId;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public int getIconId() {
    return iconId;
  }

  public void setIconId(int iconId) {
    this.iconId = iconId;
  }

  @Override
  public String toString() {
    return "ItemEntity{" +
            "desc='" + desc + '\'' +
            ", iconId=" + iconId +
            '}';
  }
}
