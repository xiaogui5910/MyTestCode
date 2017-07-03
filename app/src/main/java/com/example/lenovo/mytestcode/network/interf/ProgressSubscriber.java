package com.example.lenovo.mytestcode.network.interf;

import android.content.Context;

import com.example.lenovo.mytestcode.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by lenovo on 2017/6/16.
 */

public class ProgressSubscriber<T> extends ResourceSubscriber<T> implements ProgressCancelListener {
  private SubscriberOnNextListener<T> mListener;
  private Context mContext;
  private ProgressDialogHandler mHandler;

  public ProgressSubscriber(SubscriberOnNextListener<T> listener, Context context) {
    this.mListener = listener;
    this.mContext = context;
    mHandler = new ProgressDialogHandler(context, this, true);
  }

  private void showProgressDialog() {
    if (mHandler != null) {
      mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
    }
  }

  private void dismissProgressDialog() {
    if (mHandler != null) {
      mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
      mHandler = null;
    }
  }

  @Override
  public void onError(Throwable e) {
    if (e instanceof SocketTimeoutException) {
      ToastUtil.showToast("网络中断，请检查您的网络状态");
    } else if (e instanceof ConnectException) {
      ToastUtil.showToast("网络中断，请检查您的网络状态");
    } else {
      ToastUtil.showToast("error:" + e.getMessage());
    }
    dismissProgressDialog();
  }

  @Override
  public void onComplete() {
    dismissProgressDialog();
    ToastUtil.showToast("获取数据完成！");
  }

  /**
   * 订阅开始时调用
   * 显示ProgressDialog
   */
  @Override
  protected void onStart() {
    super.onStart();
    showProgressDialog();
  }


  @Override
  public void onNext(T t) {
    if (mListener != null) {
      mListener.onNext(t);
    }
  }

  @Override
  public void onCancelProgress() {
    if (!this.isDisposed()){
      this.dispose();
    }
  }
}
