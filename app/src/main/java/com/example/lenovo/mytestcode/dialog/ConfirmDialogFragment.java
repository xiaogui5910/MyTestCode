package com.example.lenovo.mytestcode.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.Constant;

/**
 * Created by lenovo on 2017/4/1.
 */

public class ConfirmDialogFragment extends BaseDialogFragment{

  public static ConfirmDialogFragment newIntance(String title, String message, boolean cancelable){
    ConfirmDialogFragment dialog = new ConfirmDialogFragment();
    Bundle args = new Bundle();
    args.putString(Constant.DIALOG_TITLE,title);
    args.putString(Constant.DIALOG_MESSAGE,message);
    args.putBoolean(Constant.DIALOG_CANCEL_ABLE,cancelable);
    dialog.setArguments(args);
    return dialog;
  }
  public static ConfirmDialogFragment newIntance(String title, String message,String positive,String negative, boolean cancelable){
    ConfirmDialogFragment dialog = new ConfirmDialogFragment();
    Bundle args = new Bundle();
    args.putString(Constant.DIALOG_TITLE,title);
    args.putString(Constant.DIALOG_MESSAGE,message);
    args.putString(Constant.DIALOG_POSITIVE,positive);
    args.putString(Constant.DIALOG_NEGATIVE,negative);
    args.putBoolean(Constant.DIALOG_CANCEL_ABLE,cancelable);
    dialog.setArguments(args);
    return dialog;
  }


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //去掉标题
    getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

    final View view = inflater.inflate(R.layout.dialog_scene_finished, container, false);
//    final View view = getDialogView();

    TextView tvDialogTitle = (TextView) view.findViewById(R.id.tv_dialog_title);
    TextView tvDialogMessage = (TextView) view.findViewById(R.id.tv_dialog_message);
    Button btnDialogCancel = (Button) view.findViewById(R.id.btn_dialog_cancel);
    Button btnDialogConfirm = (Button) view.findViewById(R.id.btn_dialog_confirm);

    tvDialogTitle.setText(title);
    tvDialogMessage.setText(message);
    btnDialogCancel.setText(negative);
    btnDialogConfirm.setText(positive);

    if (listener!=null){
      listener. initView(view);
    }

    btnDialogConfirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //确定，对话框消失
//        dismiss();
        if (listener!=null){
          listener.dialogPositiveListener(view);
        }
      }
    });

    btnDialogCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //取消，对话框消失
        dismiss();
        if(listener!=null){
          listener.dialogNegativeListener(view);
        }
      }
    });

    return view;
  }

  private void initView() {

  }

//  @NonNull
//  @Override
//  public Dialog onCreateDialog(Bundle savedInstanceState) {
//    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//    LayoutInflater inflater = getActivity().getLayoutInflater();
//    View view = inflater.inflate(R.layout.dialog_scene_finished, null);
//    builder.setView(view);
//    Button btnDialogCancel = (Button) view.findViewById(R.id.btn_dialog_cancel);
//    Button btnDialogConfirm = (Button) view.findViewById(R.id.btn_dialog_confirm);
//        btnDialogConfirm.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        //对话框消失
//        dismiss();
//        ToastUtil.showToast("confirm");
//        if (listener!=null){
//          listener.dialogPositiveListener();
//        }
//      }
//    });
//
//    btnDialogCancel.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        //对话框消失
//        dismiss();
//        ToastUtil.showToast("cancel");
//        if(listener!=null){
//          listener.dialogNegativeListener();
//        }
//      }
//    });
////             Add action buttons
////            .setPositiveButton("Sign in",
////                    new DialogInterface.OnClickListener()
////                    {
////                      @Override
////                      public void onClick(DialogInterface dialog, int id)
////                      {
////                      }
////                    }).setNegativeButton("Cancel", null);
//    return builder.create();
//  }


  @Override
  public void onStart() {
    super.onStart();
//    //设置fragment高度 、宽度
//    int dialogHeight = (int) (getActivity().getResources().getDisplayMetrics().heightPixels * 0.43);
//    int dialogWidth = (int) (getActivity().getResources().getDisplayMetrics().widthPixels * 0.77);
//    getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
//    getDialog().setCanceledOnTouchOutside(true);
  }
}
