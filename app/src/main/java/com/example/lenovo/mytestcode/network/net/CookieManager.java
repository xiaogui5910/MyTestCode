package com.example.lenovo.mytestcode.network.net;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by lenovo on 2017/6/14.
 */

public class CookieManager implements CookieJar {

  private static final  String TAG = "CookieManager";
  private static Context mContext;
  private static  PersistentCookieStore cookieStore;

  public CookieManager(Context context) {
    mContext = context;
    if (cookieStore==null){
      cookieStore = new PersistentCookieStore(mContext);
    }
  }

  @Override
  public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
    if (cookies!=null && cookies.size()>0){
      for (Cookie item :cookies){
        cookieStore.add(url,item);
      }
    }
  }

  @Override
  public List<Cookie> loadForRequest(HttpUrl url) {
    List<Cookie> cookies =cookieStore.get(url);
    return cookies;
  }
}
