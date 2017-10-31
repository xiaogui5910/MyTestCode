package com.example.lenovo.mytestcode.network.net;

import android.util.Log;

import com.example.lenovo.mytestcode.application.MyTestCodeApp;
import com.example.lenovo.mytestcode.utils.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/6/14.
 */

public class ReadCookiesInterceptor implements Interceptor {
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request.Builder builder = chain.request().newBuilder();
    HashSet<String> preferences = (HashSet<String>) SPUtils.get(MyTestCodeApp.context,"sp_cookies",new HashSet<>());
    for (String cookie : preferences) {
      builder.addHeader("Cookie", cookie);
      Log.d("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
    }

    return chain.proceed(builder.build());
  }
}
