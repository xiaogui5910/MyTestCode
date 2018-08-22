package com.example.lenovo.mytestcode.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lenovo.mytestcode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {


  @BindView(R.id.backdrop)
  ImageView backdrop;
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.collapsing_toolbar)
  CollapsingToolbarLayout collapsingToolbar;
  @BindView(R.id.appbar)
  AppBarLayout appbar;
  @BindView(R.id.main_content)
  CoordinatorLayout mainContent;
  @BindView(R.id.fab)
  FloatingActionButton fab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coollapsing_toolbar_layout);
    ButterKnife.bind(this);
    initView();
  }

  private void initView() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
//    SharedPreferences spf = getSharedPreferences("MyTest", MODE_PRIVATE);
//    Log.e("CollapsingToolbarLayout", "initView: spf="+spf );
  }


  @OnClick(R.id.fab)
  public void onClick() {
    EditText editText = new EditText(this);
    editText.setHint("请输入关键字");
    new AlertDialog.Builder(this)
            .setTitle("search")
            .setView(editText)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {

              }
            })
            .show();

  }
}
