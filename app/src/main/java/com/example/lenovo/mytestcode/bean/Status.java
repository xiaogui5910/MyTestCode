package com.example.lenovo.mytestcode.bean;

/**
 * Created by lenovo on 2016/11/14.
 */

public class Status {
  String name;
  String desc;
  int icon;

  public Status(String name, String desc, int icon) {
    this.name = name;
    this.desc = desc;
    this.icon = icon;
  }

  public String getName() {
    return name;
  }

  public int getIcon() {
    return icon;
  }

  public String getDesc() {
    return desc;
  }
}
