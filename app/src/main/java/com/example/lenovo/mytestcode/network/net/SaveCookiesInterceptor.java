package com.example.lenovo.mytestcode.network.net;

import com.example.lenovo.mytestcode.utils.MyTestCodeApplication;
import com.example.lenovo.mytestcode.utils.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/6/14.
 */

public class SaveCookiesInterceptor implements Interceptor {
  @Override
  public Response intercept(Chain chain) throws IOException {
    Response originalResponse = chain.proceed(chain.request());

    if (!originalResponse.headers("Set-Cookie").isEmpty()) {
      HashSet<String> cookies = new HashSet<>();

      for (String header : originalResponse.headers("Set-Cookie")) {
        cookies.add(header);
      }
      SPUtils.put(MyTestCodeApplication.context,"sp_cookies",cookies);
    }

    return originalResponse;
  }
}
