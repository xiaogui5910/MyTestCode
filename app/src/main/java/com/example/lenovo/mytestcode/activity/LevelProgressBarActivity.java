package com.example.lenovo.mytestcode.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.widget.LevelProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LevelProgressBarActivity extends AppCompatActivity {

  @BindView(R.id.level_progress_bar)
  LevelProgressBar levelProgressBar;
  @BindView(R.id.level1)
  Button level1;
  @BindView(R.id.level2)
  Button level2;
  @BindView(R.id.level3)
  Button level3;
  @BindView(R.id.level4)
  Button level4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level_progress_bar);
    ButterKnife.bind(this);

    levelProgressBar.setLevels(4);
    String[] texts = {"倔强青铜", "持续白银", "荣耀黄金", "尊贵铂金"};
    levelProgressBar.setLevelTexts(texts);
    levelProgressBar.setCurrentLevel(1);
    levelProgressBar.setAnimInterval(10);


//    WifiConfiguration wc = new WifiConfiguration();
//    wc.SSID = "\""+sr.SSID+"\"";      //<span style="color: rgb(255, 0, 0); ">这个地方一定要注意了。旁边的“是不能够省略的。密码的地方也一样。</span>
//    wc.preSharedKey = "\""+etPassword.getText().toString()+"\"";      //该热点的密码
//    wc.hiddenSSID = true;
//    wc.status = WifiConfiguration.Status.ENABLED;
//    wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
//    wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
//    wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
//    wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
//    wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//    wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//    wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);


//    WifiConfiguration config = new WifiConfiguration();
//    config.SSID = "\"" + sr.SSID + "\"";
//    config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//    int networkId = wifiManager.addNetwork(config);
//    if(networkId != -1){
//      wifiManager.enableNetwork(networkId, false);
//      wifiManager.saveConfiguration();
//    }
  }

  public static int getSecurity(ScanResult result) {
    if (result.capabilities.contains("WEP")) {
      return 1;
    } else if (result.capabilities.contains("PSK")) {
      return 2;
    } else if (result.capabilities.contains("EAP")) {
      return 3;
    }
    return 0;
  }


  @OnClick({R.id.level1, R.id.level2, R.id.level3, R.id.level4})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.level1:
//        levelProgressBar.setCurrentLevel(1);
//        levelProgressBar.setAnimInterval(10);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri newsurl = Uri.parse("https://123.sogou.com/");
        intent.setData(newsurl);
        startActivity(intent);
        break;
      case R.id.level2:
        levelProgressBar.setCurrentLevel(2);
        levelProgressBar.setAnimInterval(10);
        break;
      case R.id.level3:
        levelProgressBar.setCurrentLevel(3);
        levelProgressBar.setAnimInterval(10);
        break;
      case R.id.level4:
        levelProgressBar.setCurrentLevel(4);
        levelProgressBar.setAnimInterval(10);
//        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)); //直接进入手机中的wifi网络设置界面
        break;
    }
  }
}
