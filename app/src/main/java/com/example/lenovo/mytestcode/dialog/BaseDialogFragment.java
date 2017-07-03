package com.example.lenovo.mytestcode.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.Constant;


/**
 * Created by lenovo on 2017/4/1.
 */

public class BaseDialogFragment extends DialogFragment {
  protected String title;
  protected String message;
  protected String positive;
  protected String negative;
  protected boolean cancelable;
  protected View dialogView;

  protected OnDialogFragmentListener listener;

  public void setOnDialogFragmentListener(OnDialogFragmentListener listener) {
    this.listener = listener;
  }

  public interface OnDialogFragmentListener {
    void initView(View view);
    void dialogPositiveListener(View view);

    void dialogNegativeListener(View view);
  }

  public View getDialogView() {
    return dialogView;
  }

  public void setDialogView(View dialogView) {
    this.dialogView = dialogView;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() == null) {
      return;
    }
    //解析
    parseArgs(getArguments());
    //设置是否可以点击消失
    setCancelable(cancelable);
  }

  /**
   * 从bundle中解析参数，args有可能来自fragment被系统回收，然后界面又重新被启动系统保存的参数；也有可能是其他使用者带过来的
   * ，具体实现交给子类
   */
  protected void parseArgs(Bundle args) {
    title = args.getString(Constant.DIALOG_TITLE,getString(R.string.title));
    message = args.getString(Constant.DIALOG_MESSAGE,getString(R.string.message));
    positive = args.getString(Constant.DIALOG_POSITIVE,getString(R.string.positive));
    negative = args.getString(Constant.DIALOG_NEGATIVE,getString(R.string.negative));
    cancelable = args.getBoolean(Constant.DIALOG_CANCEL_ABLE,true);
  }

}
