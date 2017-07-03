package com.example.lenovo.mytestcode.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.receiver.AlarmReceiver;
import com.example.lenovo.mytestcode.utils.DensityUtils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmManagerActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

  private static final String TAG = "AlarmManagerActivity";
  @Bind(R.id.tv_speed)
  TextView tvSpeed;
  @Bind(R.id.bt_stop_cycle)
  Button btStopCycle;
  @Bind(R.id.ll_bottom)
  LinearLayout llBottom;
  @Bind(R.id.activity_alarm_manager)
  RelativeLayout activityAlarmManager;
  @Bind(R.id.seekBar)
  SeekBar seekBar;
  @Bind(R.id.seekBar1)
  SeekBar seekBar1;
  @Bind(R.id.seekbar_slider_time)
  TextView seekbarSliderTime;
  @Bind(R.id.ll_test1)
  LinearLayout llTest1;
  @Bind(R.id.tx_currentTime)
  TextView txCurrentTime;
  @Bind(R.id.tx_maxTime)
  TextView txMaxTime;
  @Bind(R.id.seekBar_layout)
  LinearLayout seekBarLayout;
  @Bind(R.id.iv_circle)
  ImageView ivCircle;
  @Bind(R.id.iv_thumb)
  ImageView ivThumb;
  @Bind(R.id.rl_thumb_bg)
  RelativeLayout rlThumbBg;


  private long lastTotalRxBytes = 0;
  private long lastTimeStamp = 0;

  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
    }
  };
  private Context mContext;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_alarm_manager);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    mContext = this;
    Log.e("111", "init:uid= " + getApplicationInfo().uid);
    lastTotalRxBytes = getTotalRxBytes();
    lastTimeStamp = System.currentTimeMillis();
    new Timer().schedule(task, 1000, 2000); // 1s后启动任务，每2s执行一次

    seekBar.setOnSeekBarChangeListener(this);
    seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int max = seekBar.getMax();
        int margin = (int) (seekBar.getWidth() * (progress * 1f / max));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlThumbBg.getLayoutParams();
        Log.e(TAG, "onProgressChanged:margin= " + margin + ",percent" + (progress * 1f / max));
//    params.rightMargin = margin;
        if (margin<= DensityUtils.dp2px(AlarmManagerActivity.this,14)){
          margin = DensityUtils.dp2px(AlarmManagerActivity.this,14);
        }
        params.width = margin;
        params.height = rlThumbBg.getHeight();
        rlThumbBg.setLayoutParams(params);
//        int visibility = rlThumbBg.getVisibility();
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        int max = seekBar.getMax();
        int margin = (int) (seekBar.getWidth() * (progress * 1f / max));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlThumbBg.getLayoutParams();
        Log.e(TAG, "onProgressChanged:margin= " + margin + ",percent" + (progress * 1f / max));
//    params.rightMargin = margin;
        params.width = margin;
        params.height = rlThumbBg.getHeight();
        rlThumbBg.setLayoutParams(params);
        Log.e(TAG, "onStartTrackingTouch: 0000000");
    rlThumbBg.setVisibility(View.VISIBLE);
      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        Log.e(TAG, "onStopTrackingTouch: 11111111111");
    rlThumbBg.setVisibility(View.INVISIBLE);
      }

    });
  }

  @OnClick({R.id.bt_start_once, R.id.bt_start_cycle, R.id.bt_stop_cycle, R.id.fl_scene_more})
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
      case R.id.fl_scene_more:
        showPopWindow();
        break;
    }
  }

  private void showPopWindow() {
    showPopupWindow(activityAlarmManager);
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

  private void showNetSpeed() {

    long nowTotalRxBytes = getTotalRxBytes();
    long nowTimeStamp = System.currentTimeMillis();
    long speed = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换

    lastTimeStamp = nowTimeStamp;
    lastTotalRxBytes = nowTotalRxBytes;

    Message msg = mHandler.obtainMessage();
    msg.what = 100;
    msg.obj = String.valueOf(speed) + " kb/s";

    mHandler.sendMessage(msg);//更新界面
  }


  private long getTotalRxBytes() {
    return TrafficStats.getUidRxBytes(getApplicationInfo().uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);//转为KB
  }

  TimerTask task = new TimerTask() {
    @Override
    public void run() {
      showNetSpeed();
    }
  };

  private void showPopupWindow(View view) {

    // 一个自定义的布局，作为显示的内容
    View contentView = LayoutInflater.from(mContext).inflate(
            R.layout.pop_window, null);
    // 设置按钮的点击事件
    Button button = (Button) contentView.findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Toast.makeText(mContext, "button is pressed",
                Toast.LENGTH_SHORT).show();
      }
    });

    final PopupWindow popupWindow = new PopupWindow(contentView,
            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);

    popupWindow.setTouchable(true);

    popupWindow.setTouchInterceptor(new View.OnTouchListener() {

      @Override
      public boolean onTouch(View v, MotionEvent event) {

        Log.i("mengdd", "onTouch : ");

        return false;
        // 这里如果返回true的话，touch事件将被拦截
        // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
      }
    });
    //设置进入动画
    popupWindow.setAnimationStyle(R.style.AppPopupAnimation);
    // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
    // 我觉得这里是API的一个bug
    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    // 设置好参数之后再show
//    popupWindow.showAsDropDown(view, 0, -view.getHeight());
    //设置layout在PopupWindow中显示的位置
    popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
  }



  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//    if (fromUser) {
//      seekbarSliderTime.setText(updateCurrentTimeText(progress));
//    }
//    tx_currentTime.setText(updateCurrentTimeText(progress));
//    if (progress == seekBar.getMax()) {
//      pauseIcon.setLayoutParams(miss);
//      playIcon.setLayoutParams(show);
//    }
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
    Log.d(TAG, "onStartTrackingTouch");
//    isUserPressThumb = true;
//    Seekbar_slider_time.setVisibility(View.VISIBLE);
    //设置seekbar高度，解决第一次按下后Thumb被遮盖的问题
//    LayoutParams lp = seekBar.getLayoutParams();
//    lp.height *= 4;
//    seekBar.setLayoutParams(lp);
//    //设置seekbarThumb相对位置可大于进度条15，保证thumb在变成40dp直径后可以滑动到进度条最末尾
//    seekBar.setThumbOffset(15);
//    seekBar.setThumb(Thumb_pressed);
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
    Log.d(TAG, "onStopTrackingTouch");
//    mi.seekTo(seekBar.getProgress());
//    seekBar.setThumbOffset(0);
//    seekBar.setThumb(Thumb_normal);
//    Seekbar_slider_time.setVisibility(View.INVISIBLE);
//    isUserPressThumb = false;
  }

}
