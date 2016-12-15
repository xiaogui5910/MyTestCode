package com.example.lenovo.mytestcode.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by lenovo on 2016/12/15.
 */

public class AlarmReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    if(intent.getAction().equals("alarm_short")){
      Toast.makeText(context, "short alarm", Toast.LENGTH_SHORT).show();
    }else{
      Toast.makeText(context, "repeating alarm",
              Toast.LENGTH_SHORT).show();
    }
  }
}
