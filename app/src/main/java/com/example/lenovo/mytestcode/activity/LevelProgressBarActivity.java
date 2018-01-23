package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.widget.LevelProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LevelProgressBarActivity extends AppCompatActivity {

  @Bind(R.id.level_progress_bar)
  LevelProgressBar levelProgressBar;
  @Bind(R.id.level1)
  Button level1;
  @Bind(R.id.level2)
  Button level2;
  @Bind(R.id.level3)
  Button level3;
  @Bind(R.id.level4)
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
  }

  @OnClick({R.id.level1, R.id.level2, R.id.level3, R.id.level4})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.level1:
        levelProgressBar.setCurrentLevel(1);
        levelProgressBar.setAnimInterval(10);
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
        break;
    }
  }
}
