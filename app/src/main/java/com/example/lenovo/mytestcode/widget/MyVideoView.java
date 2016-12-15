package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by lenovo on 2016/11/29.
 */

public class MyVideoView extends VideoView {
  public MyVideoView(Context context) {
    super(context);
  }

  public MyVideoView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
  }

  public void playVideo(Uri uri) {
    if (uri == null) {
      return;
    }
    //设置播放路径
    setVideoURI(uri);

    //开始播放
    start();

    //循环播放
    setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
      @Override
      public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.setLooping(true);
      }
    });

    setOnErrorListener(new MediaPlayer.OnErrorListener() {
      @Override
      public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return true;
      }
    });
  }
}
