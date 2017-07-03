package com.example.lenovo.mytestcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.dingmouren.paletteimageview.PaletteImageView;
import com.example.lenovo.mytestcode.R;

public class PaletteImageViewActivity extends AppCompatActivity {
  private SeekBar mSeekBar;
  private PaletteImageView paletteImageView1,paletteImageView2,paletteImageView3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_palette_image_view);

    mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
    paletteImageView1 = (PaletteImageView) findViewById(R.id.palette1);
    paletteImageView2 = (PaletteImageView) findViewById(R.id.palette2);
    paletteImageView3 = (PaletteImageView) findViewById(R.id.palette3);
    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        paletteImageView1.setCornerRadius(progress);
        paletteImageView2.setCornerRadius(progress);
        paletteImageView3.setCornerRadius(progress);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }
}
