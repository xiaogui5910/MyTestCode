package com.example.view_texst;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/12/26.
 */
public class a extends Application{
    public static  Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
