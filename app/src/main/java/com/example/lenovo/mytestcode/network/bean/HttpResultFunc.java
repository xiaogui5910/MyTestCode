package com.example.lenovo.mytestcode.network.bean;

import io.reactivex.functions.Function;

/**
 * Created by lenovo on 2017/6/16.
 */

public class HttpResultFunc<T> implements Function<HttpResult<T>,T> {
  @Override
  public T apply(HttpResult<T> tHttpResult) throws Exception {
    if (tHttpResult.getResultCode()!=0){
      throw new ApiException(tHttpResult.getResultCode());
    }
    return tHttpResult.getData();
  }
}
