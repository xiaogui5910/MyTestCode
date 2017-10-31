package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.mytestcode.R;

import butterknife.ButterKnife;

public class ZXingActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zxing);
    ButterKnife.bind(this);
  }

}
