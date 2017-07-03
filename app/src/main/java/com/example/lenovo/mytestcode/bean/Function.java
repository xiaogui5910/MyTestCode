package com.example.lenovo.mytestcode.bean;

/**
 * Created by lenovo on 2017/5/26.
 */

public class Function {
  String name;
  String icon;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  @Override
  public String toString() {
    return "Function{" +
            "name='" + name + '\'' +
            ", icon='" + icon + '\'' +
            '}';
  }
}
