package com.example.lenovo.mytestcode.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lenovo on 2016/12/12.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
//    Log.e(TAG, "onReceive: " );
//    PackageManager manager = context.getPackageManager();
//    if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
//      String packageName = intent.getData().getSchemeSpecificPart();
//      Log.e(TAG, "onReceive: 安装成功" );
//      Toast.makeText(context, "安装成功"+packageName, Toast.LENGTH_LONG).show();
//    }
//    if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
//      String packageName = intent.getData().getSchemeSpecificPart();
//      Log.e(TAG, "onReceive: 卸载成功" );
//      Toast.makeText(context, "卸载成功"+packageName, Toast.LENGTH_LONG).show();
//    }
//    if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
//      String packageName = intent.getData().getSchemeSpecificPart();
//      Log.e(TAG, "onReceive: 替换成功" );
//      Toast.makeText(context, "替换成功"+packageName, Toast.LENGTH_LONG).show();
//    }
//    if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
//      Log.e(TAG, "onReceive: 拨打号码" );
//      Intent intent1 = new Intent(context, CollapsingToolbarLayoutActivity.class);
//      intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//      context.startActivity(intent1);
//      Toast.makeText(context, "拨打号码"+getResultData(), Toast.LENGTH_LONG).show();
//    }
  }
}
