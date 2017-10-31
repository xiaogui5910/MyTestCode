package com.example.lenovo.mytestcode.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.bean.PieData;
import com.example.lenovo.mytestcode.widget.CheckView;
import com.example.lenovo.mytestcode.widget.LeafLoadingView;
import com.example.lenovo.mytestcode.widget.PieView;
import com.example.lenovo.mytestcode.widget.PostManView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

  private static final int MID_VALUE = 100;
  @Bind(R.id.postmanview)
  PostManView postmanview;
  @Bind(R.id.pieView)
  PieView pieView;
  @Bind(R.id.random)
  Button random;
  @Bind(R.id.check_view)
  CheckView checkView;
  @Bind(R.id.check)
  Button check;
  @Bind(R.id.uncheck)
  Button uncheck;
  @Bind(R.id.leaf_loading_view)
  LeafLoadingView mLeafLoadingView;
  @Bind(R.id.iv_change_img)
  ImageView ivChangeImg;
  @Bind(R.id.sb_color)
  SeekBar sbColor;
  @Bind(R.id.sb_saturation)
  SeekBar sbSaturation;
  @Bind(R.id.sb_light)
  SeekBar sbLight;
  private List<PieData> list;
  private boolean isOrder;

  private static final int REFRESH_PROGRESS = 0x10;
  private int mProgress = 0;

  Handler mHandler = new Handler() {
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case REFRESH_PROGRESS:
          if (mProgress < 40) {
            mProgress += 1;
            // 随机800ms以内刷新一次
            mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                    new Random().nextInt(800));
            mLeafLoadingView.setProgress(mProgress);
          } else {
            mProgress += 1;
            // 随机1200ms以内刷新一次
            mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                    new Random().nextInt(1200));
            mLeafLoadingView.setProgress(mProgress);

          }
          break;

        default:
          break;
      }
    }

    ;
  };
  private Bitmap bitmap;
  private float hue;
  private float saturation;
  private float lum;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom_view);
    ButterKnife.bind(this);

    postmanview.start();

    list = new ArrayList<>();
    initData();
    isOrder = true;
    pieView.setDataList(list);
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_6);

    sbColor.setOnSeekBarChangeListener(this);
    sbLight.setOnSeekBarChangeListener(this);
    sbSaturation.setOnSeekBarChangeListener(this);
  }

  private void initData() {
    for (int i = 0; i < 5; i++) {
      PieData pieData = new PieData();
      int j = isOrder ? i : 5 - i;
      pieData.setName("name" + j);
      pieData.setValue(j * 10);
      list.add(pieData);
    }

    mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS, 3000);
  }

  @OnClick({R.id.check, R.id.uncheck, R.id.random})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.check:
        checkView.check();
        break;
      case R.id.uncheck:
        checkView.unCheck();
        break;
      case R.id.random:
        list.clear();
        isOrder = !isOrder;
        initData();
        pieView.setDataList(list);
        break;
    }
  }

  public Bitmap handleBitmapStatus(Bitmap bitmap,float hue,float saturation,float lum){
    Bitmap b = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(b);
    Paint paint = new Paint();
    //色调
    ColorMatrix rotateMatrix = new ColorMatrix();
    rotateMatrix.setRotate(0,hue);
    rotateMatrix.setRotate(1,hue);
    rotateMatrix.setRotate(2,hue);
    //饱和度
    ColorMatrix saturationMatrix = new ColorMatrix();
    saturationMatrix.setSaturation(saturation);
    //亮度
    ColorMatrix scaleMatrix = new ColorMatrix();
    scaleMatrix.setScale(lum,lum,lum,1);


    ColorMatrix imgMatrix = new ColorMatrix();
    imgMatrix.postConcat(rotateMatrix);
    imgMatrix.postConcat(saturationMatrix);
    imgMatrix.postConcat(scaleMatrix);

    paint.setColorFilter(new ColorMatrixColorFilter(imgMatrix));
    canvas.drawBitmap(bitmap,0,0,paint);
    return b;
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    int id = seekBar.getId();
    switch (id){
      case R.id.sb_color:
        hue = (progress - MID_VALUE) * 1.0f/MID_VALUE * 180;
        break;
      case R.id.sb_saturation:
        saturation = progress * 1.0f / MID_VALUE;
        break;
      case R.id.sb_light:
        lum = progress * 1.0F / MID_VALUE;
        break;
    }
    ivChangeImg.setImageBitmap(handleBitmapStatus(bitmap,hue,saturation,lum));
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {

  }
}
