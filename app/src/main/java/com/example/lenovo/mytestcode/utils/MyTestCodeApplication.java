package com.example.lenovo.mytestcode.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by lenovo on 2017/1/12.
 */

public class MyTestCodeApplication extends Application{
  public static Context context;
  @Override
  public void onCreate() {
    super.onCreate();
    this.context =this;
  }
}
