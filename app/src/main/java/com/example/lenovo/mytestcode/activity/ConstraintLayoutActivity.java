package com.example.lenovo.mytestcode.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.mytestcode.R;

import java.util.List;

public class ConstraintLayoutActivity extends AppCompatActivity {

  private String TAG = "ConstraintLayoutActivity";
  private List<WifiConfiguration> existingConfigs;
  private WifiManager wifiManager;
  private boolean isNetworkRegistered;
  private WifiAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_constraint_layout);

    View testView = LayoutInflater.from(this).inflate(R.layout.layout_test, null);

    String extPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    String dataDir = Environment.getDataDirectory().getAbsolutePath();
    Log.e(TAG, "onCreate: extPath=" + extPath);
    Log.e(TAG, "onCreate: dataDir=" + dataDir);
    String external_storage = System.getenv("EXTERNAL_STORAGE");
    Log.e(TAG, "onCreate: external_storage=" + external_storage);

    registerNetworkChangeListener();

    wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
    existingConfigs = wifiManager.getConfiguredNetworks();

    wifiManager.startScan();
    List<ScanResult> scanResults = wifiManager.getScanResults();

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_wifi_test);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    adapter = new WifiAdapter(R.layout.item_wifi, scanResults);
    recyclerView.setAdapter(adapter);
    DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    recyclerView.addItemDecoration(decor);

    recyclerView.addOnItemTouchListener(new OnItemClickListener() {
      @Override
      public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        final ScanResult scanResult = (ScanResult) baseQuickAdapter.getItem(i);
        boolean isSave = checkSaveStatus(scanResult.SSID);
        if (!openWifi()) {
          return;
        }
        if (isSave) {
          WifiConfiguration wifiConfig = getWifiConfig(scanResult.SSID);
          if (wifiConfig != null) {
            if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {

              boolean enableNetwork = wifiManager.enableNetwork(wifiConfig.networkId, true);
              wifiManager.saveConfiguration();
              Log.e(TAG, "SimpleOnItemClick: enableNetwork=" + enableNetwork);
            }
          }
        } else {
          final EditText editText = new EditText(ConstraintLayoutActivity.this);
          editText.setHint("wifi密码");
          new AlertDialog.Builder(ConstraintLayoutActivity.this)
                  .setTitle("请输入密码").setView(editText).setPositiveButton("连接", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              WifiConfiguration wifiConfig = createWifiInfo(scanResult.SSID, editText.getText().toString().trim(), 3, wifiManager);
              if (wifiConfig != null) {
                if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {

                  boolean enableNetwork = wifiManager.enableNetwork(wifiConfig.networkId, true);
                  wifiManager.saveConfiguration();
                  Log.e(TAG, "SimpleOnItemClick: enableNetwork=" + enableNetwork);
                }
              }
            }
          }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
          }).show();
        }

      }
    });
  }

  private boolean openWifi() {
    boolean isOpenWifi = true;
    if (!wifiManager.isWifiEnabled()) {
      isOpenWifi = wifiManager.setWifiEnabled(true);
    }
    return isOpenWifi;
  }

  private boolean closeWifi() {
    if (wifiManager.isWifiEnabled()) {
      return true;
    } else {
      return wifiManager.setWifiEnabled(false);
    }
  }

  class WifiAdapter extends BaseQuickAdapter<ScanResult> {

    public WifiAdapter(int layoutResId, List<ScanResult> data) {
      super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ScanResult scanResult) {
      WifiInfo connectionInfo = wifiManager.getConnectionInfo();
      String ssid = connectionInfo.getSSID();
      ssid = ssid.replace("\"", "");
      String level = getLevelText(scanResult.level);
      boolean isSave = checkSaveStatus(scanResult.SSID);
      baseViewHolder.setText(R.id.name, TextUtils.isEmpty(scanResult.SSID) ? "" : scanResult.SSID)
              .setText(R.id.level, level)
              .setTextColor(R.id.save, isSave ? Color.BLUE : Color.GRAY)
              .setText(R.id.save, isSave ? "已保存" : "未知");
      if (TextUtils.equals(ssid, scanResult.SSID)) {
        baseViewHolder.setText(R.id.save, "已连接");
      }
    }
  }

  private boolean checkSaveStatus(String ssid) {
    if (TextUtils.isEmpty(ssid)) {
      return false;
    }
    if (existingConfigs != null) {
      for (int i = 0; i < existingConfigs.size(); i++) {
        WifiConfiguration wifiConfiguration = existingConfigs.get(i);
        String ssid_str = wifiConfiguration.SSID;

        if (TextUtils.isEmpty(ssid_str)) {
          continue;
        }

        String str2 = ssid_str.replace("\"", "");


        if (TextUtils.equals(str2, ssid)) {
          return true;
        }
      }
    }
    return false;
  }

  private WifiConfiguration getWifiConfig(String ssid) {
    if (TextUtils.isEmpty(ssid)) {
      return null;
    }
    if (existingConfigs != null) {
      for (int i = 0; i < existingConfigs.size(); i++) {
        WifiConfiguration wifiConfiguration = existingConfigs.get(i);
        String ssid_str = wifiConfiguration.SSID;

        if (TextUtils.isEmpty(ssid_str)) {
          continue;
        }

        String str2 = ssid_str.replace("\"", "");


        if (TextUtils.equals(str2, ssid)) {
          return wifiConfiguration;
        }
      }
    }
    return null;
  }


  /**
   * Level>-50           信号最强4格
   * <p>
   * -50<Level<-65  信号3格
   * <p>
   * -65<Level<-75  信号2格
   * <p>
   * -75<Level<-90  信号1格
   * <p>
   * -90<Level          信号0格
   *
   * @param level
   * @return
   */
  private String getLevelText(int level) {
    String level_str = "0格";
    if (level > -50) {
      level_str = "4格";
    } else if (level > -65) {
      level_str = "3格";
    } else if (level > -75) {
      level_str = "2格";
    } else if (level < -90) {
      level_str = "1格";
    }
    return level_str;
  }

  /**
   * 注册网络状态广播监听
   */
  protected void registerNetworkChangeListener() {
    IntentFilter mFilter = new IntentFilter();
    mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
    registerReceiver(networkReceiver, mFilter);
    isNetworkRegistered = true;
  }

  /**
   * 移除网络状态监听
   */
  public void doUnregisterNetworkReceiver() {
    if (isNetworkRegistered) {
      if (networkReceiver != null) {
        unregisterReceiver(networkReceiver);
      }
      isNetworkRegistered = false;
    }
  }

  /**
   * 网络状态广播接收者
   */
  private BroadcastReceiver networkReceiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
      if (adapter != null) {
        adapter.notifyDataSetChanged();
      }
    }
  };

  @Override
  protected void onDestroy() {
    super.onDestroy();
    doUnregisterNetworkReceiver();
  }

  public WifiConfiguration createWifiInfo(String SSID,
                                          String Password, int Type, WifiManager wifiManager) {
    WifiConfiguration config = new WifiConfiguration();
    config.allowedAuthAlgorithms.clear();
    config.allowedGroupCiphers.clear();
    config.allowedKeyManagement.clear();
    config.allowedPairwiseCiphers.clear();
    config.allowedProtocols.clear();
    config.SSID = "\"" + SSID + "\"";

    WifiConfiguration tempConfig = getWifiConfig(SSID);
    if (tempConfig != null) {
      wifiManager.removeNetwork(tempConfig.networkId);
    }

    if (Type == 1) // WIFICIPHER_NOPASS
    {
      config.wepKeys[0] = "";
      config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
      config.wepTxKeyIndex = 0;
    }
    if (Type == 2) // WIFICIPHER_WEP
    {
      config.hiddenSSID = true;
      config.wepKeys[0] = "\"" + Password + "\"";
      config.allowedAuthAlgorithms
              .set(WifiConfiguration.AuthAlgorithm.SHARED);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
      config.allowedGroupCiphers
              .set(WifiConfiguration.GroupCipher.WEP104);
      config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
      config.wepTxKeyIndex = 0;
    }
    if (Type == 3) // WIFICIPHER_WPA
    {
      config.preSharedKey = "\"" + Password + "\"";
      config.hiddenSSID = true;
      config.allowedAuthAlgorithms
              .set(WifiConfiguration.AuthAlgorithm.OPEN);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
      config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
      config.allowedPairwiseCiphers
              .set(WifiConfiguration.PairwiseCipher.TKIP);
      // config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
      config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
      config.allowedPairwiseCiphers
              .set(WifiConfiguration.PairwiseCipher.CCMP);
      config.status = WifiConfiguration.Status.ENABLED;
    }
    return config;
  }

}


