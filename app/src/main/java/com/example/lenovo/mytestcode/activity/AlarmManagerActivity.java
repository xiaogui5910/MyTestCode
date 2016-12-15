package com.example.lenovo.mytestcode.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.receiver.AlarmReceiver;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmManagerActivity extends AppCompatActivity {

  @Bind(R.id.activity_alarm_manager)
  LinearLayout activityAlarmManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alarm_manager);
    ButterKnife.bind(this);
    init();
  }

  private void init() {

  }

  @OnClick({R.id.bt_start_once, R.id.bt_start_cycle, R.id.bt_stop_cycle})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.bt_start_once:
        startOnce();
        break;
      case R.id.bt_start_cycle:
        startCycle();
        break;
      case R.id.bt_stop_cycle:
        stopCycle();
        break;
    }
  }

  private void stopCycle() {
    Intent intent = new Intent(this, AlarmReceiver.class);
    intent.setAction("alarm_repeating");
    PendingIntent sender = PendingIntent
            .getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
    alarm.cancel(sender);
  }

  private void startCycle() {
    Intent intent = new Intent(this, AlarmReceiver.class);
    intent.setAction("alarm_repeating");
    PendingIntent sender = PendingIntent
            .getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    //开始时间
    long firstTime = SystemClock.elapsedRealtime();

    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
    //5秒一个周期，不停的发送广播
    am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP
            , firstTime, 5 * 1000, sender);
  }

  private void startOnce() {
    //操作：发送一个广播，广播接收后Toast提示定时操作完成
    Intent intent = new Intent(this, AlarmReceiver.class);
    intent.setAction("alarm_short");
    PendingIntent sender =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            PendingIntent.getBroadcast(this, UUID.randomUUID().hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

    //设定一个五秒后的时间
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.add(Calendar.SECOND, 5);

    AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
    alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    //或者以下面方式简化
    //alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5*1000, sender);

    Toast.makeText(this, "五秒后alarm开启", Toast.LENGTH_LONG).show();
  }
}
