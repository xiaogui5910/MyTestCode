package com.example.lenovo.mytestcode.network.bean;

import com.example.lenovo.mytestcode.network.interf.RetrofitException;

import io.reactivex.functions.Function;

/**
 * Created by lenovo on 2017/6/16.
 */

public class HttpResultFunc<T> implements Function<HttpResult<T>,T> {
  @Override
  public T apply(HttpResult<T> tHttpResult) throws Exception {
    if (tHttpResult.getCode()!=0){
      throw new RetrofitException(tHttpResult.getCode());
    }
    return tHttpResult.getData();
  }
}
