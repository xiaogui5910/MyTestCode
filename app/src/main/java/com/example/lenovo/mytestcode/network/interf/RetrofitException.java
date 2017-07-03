package com.example.lenovo.mytestcode.network.interf;

import android.os.Looper;

import com.example.lenovo.mytestcode.utils.ToastUtil;

/**
 * 统一处理网络异常errorCode
 * Created by lenovo on 2017/6/28.
 */

public class RetrofitException extends RuntimeException {

  private static final int ERROR_TOKEN = 100;
  private static final int ERROR_NETWORK = ERROR_TOKEN + 1;
  private static final int ERROR_SERVER = ERROR_NETWORK + 1;

  public RetrofitException(int code) {
    this(getErrorMessage(code));
  }

  public RetrofitException(String msg) {
    super(msg);
  }

  private static String getErrorMessage(int code) {
    String msg;
    switch (code) {
      case ERROR_TOKEN:
        msg = "token过期，请重新登陆！";
        break;
      case ERROR_NETWORK:
        msg = "网络错误，请检查网络连接设置！";
        break;
      case ERROR_SERVER:
        msg = "服务器内部错误，请稍后再试！";
        break;
      default:
        msg = "error";
        break;
    }
    showToast(msg);
    return msg;
  }

  private static void showToast(String msg) {
    Looper.prepare();
    ToastUtil.showToast(msg);
    Looper.loop();
  }

}
