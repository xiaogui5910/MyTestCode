package com.example.lenovo.mytestcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      TextView tv1 = (TextView) findViewById(R.id.tv1);
      tv1.setText("nihaome?");
    }
}
