package com.example.lenovo.mytestcode.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class DownloadManagerActivity extends AppCompatActivity {

  private static final String TAG = "DownloadManagerActivity";
  @Bind(R.id.tv_progress)
  TextView tvProgress;
  @Bind(R.id.pb_progress)
  ProgressBar pbProgress;
  @Bind(R.id.tv_total)
  TextView tvTotal;
  @Bind(R.id.btn_start)
  Button btnStart;
  @Bind(R.id.btn_query)
  Button btnPause;
  @Bind(R.id.btn_cancel)
  Button btnCancel;
  private DownloadManager downloadManager;
  private long downloadId;
  private DownloadManager.Request request;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_download_manager);
    ButterKnife.bind(this);

    initView();
    initData();
  }

  /**
   * 网络状态广播接收者
   */
  private BroadcastReceiver networkReceiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {

      String action = intent.getAction();
      Log.e(TAG, "onReceive: action=" + action);
    }

  };

  private void initView() {
    IntentFilter mFilter = new IntentFilter();
    mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
    registerReceiver(networkReceiver, mFilter);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (networkReceiver != null) {
      unregisterReceiver(networkReceiver);
    }
  }

  private void initData() {
    downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    String url = "https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk";
    request = new DownloadManager.Request(Uri.parse(url));
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
    request.setTitle("下载silkwave");
    request.setDescription("可以观看电台、电视的手机应用");
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "silkwave.apk");


  }

  @OnClick({R.id.btn_start, R.id.btn_query, R.id.btn_cancel})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_start:
        startDownload();
        break;
      case R.id.btn_query:
        queryStatus();
        ToastUtil.showToast("");
        break;
      case R.id.btn_cancel:
        downloadManager.remove(downloadId);
        ToastUtil.showToast("下载已取消");
        break;
    }
  }

  private void startDownload() {
    RxPermissions rxPermissions = new RxPermissions(this);
    rxPermissions.request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
            .subscribe(new Consumer<Boolean>() {
              @Override
              public void accept(Boolean granted) throws Exception {
                if (granted) { // 在android 6.0之前会默认返回true
                  // 已经获取权限
                  downloadId = downloadManager.enqueue(request);
                  ToastUtil.showToast("开始下载...");
                } else {
                  // 未获取权限
                  ToastUtil.showToast("没有获取到权限，下载取消");
                }
              }
            });
  }

  private void queryStatus() {
    DownloadManager.Query query = new DownloadManager.Query();
    query.setFilterById(downloadId);
    Cursor cursor = downloadManager.query(query);
    if (cursor != null && cursor.moveToFirst()) {
      String statusMsg = "";
      int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
      int reason = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
      String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
      int bytesDL = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
      int fileSize = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

      int fileUriIdx = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
      String fileUri = cursor.getString(fileUriIdx);
      String fileName = null;
      if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        if (fileUri != null) {
          fileName = Uri.parse(fileUri).getPath();
        }
      } else {
        //Android 7.0以上的方式：请求获取写入权限，这一步报错
        //过时的方式：DownloadManager.COLUMN_LOCAL_FILENAME
        int fileNameIdx = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
        fileName = cursor.getString(fileNameIdx);
      }

      switch (status) {
        case DownloadManager.STATUS_FAILED:
          statusMsg = "failed";
          break;
        case DownloadManager.STATUS_SUCCESSFUL:
          statusMsg = "successful";
          break;
        case DownloadManager.STATUS_PAUSED:
          statusMsg = "pause";
          break;
        case DownloadManager.STATUS_RUNNING:
          statusMsg = "running";
          break;
      }
      ToastUtil.showToast(statusMsg);

      //错误信息
      String reasonString = "Unknown";
      switch (reason) {
        case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
          reasonString = "Waiting for WiFi";
          break;
        case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
          reasonString = "Waiting for connectivity";
          break;
        case DownloadManager.PAUSED_WAITING_TO_RETRY:
          reasonString = "Waiting to retry";
          break;
        default:
          break;
      }
      // Construct a status summary
      StringBuilder sb = new StringBuilder();
      sb.append(title).append("\n");
      sb.append(reasonString).append("\n");
      sb.append("Downloaded ").append(bytesDL).append(" / ").append(fileSize).append("\n");
      sb.append("url=").append(fileUri).append("\n");
      sb.append("fileName=").append(fileName);

      String totalSize = Formatter.formatFileSize(this, fileSize);
      String progress = Formatter.formatFileSize(this, bytesDL);
      tvTotal.setText(totalSize);
      tvProgress.setText(progress);

      ToastUtil.showToast(sb.toString());
      Log.e(TAG, "queryStatus: " + sb.toString());

    }
  }
}
