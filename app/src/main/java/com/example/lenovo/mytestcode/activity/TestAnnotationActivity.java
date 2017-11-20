package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.testannotation.AnnotatedTarget;
import com.example.lenovo.mytestcode.testannotation.TestAnnotationProcessor;
import com.example.lenovo.mytestcode.testannotation.testdemo.Bind;
import com.example.lenovo.mytestcode.testannotation.testdemo.ContentView;
import com.example.lenovo.mytestcode.testannotation.testdemo.OnClick;
import com.example.lenovo.mytestcode.testannotation.testdemo.Shaver;
import com.example.lenovo.mytestcode.testannotation.testdemo.StringRes;

@ContentView(R.layout.activity_test_annotation)
public class TestAnnotationActivity extends AppCompatActivity {

  @Bind(R.id.btn1)
  Button btn1;
  @Bind(R.id.btn2)
  Button btn2;
  @Bind(R.id.tv1)
  TextView tv1;
  @Bind(R.id.et1)
  EditText et1;

  @StringRes(R.string.string_shaver)
  String stringRes;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_test_annotation);

    TestAnnotationProcessor.process(AnnotatedTarget.class);

    Shaver.bind(this);
    Log.e("TestAnnotationActivity", btn1.getText().toString() + " , " + btn2.getText().toString() + " , " + tv1.getText().toString() + " , " + et1.getText().toString());
    tv1.setText(stringRes);
  }

  @OnClick({R.id.btn1,R.id.btn2})
  public void onClicks(View view){
    switch (view.getId()){
      case R.id.btn1:
        Toast.makeText(this, "点击了btn1", Toast.LENGTH_SHORT).show();
        break;
      case R.id.btn2:
        Toast.makeText(this, "点击了btn2", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
