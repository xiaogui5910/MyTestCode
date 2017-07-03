package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.lenovo.mytestcode.R;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;


/**
 * Created by lenovo on 2017/1/17.
 */

public class MusicSeekBar extends RelativeLayout {

  private SeekBar seekBar;
  private RelativeLayout rlThumbBg;
  private int rlThumbBgHeight;
  private int rlThumbBgWidth;
  private ImageView ivCircle;
  private int ivCircleHeight;
  private int ivCircleWidth;
  private int seekBarHeight;
  private int seekBarWidth;

  public MusicSeekBar(Context context) {
    super(context);
    init();
  }

  public MusicSeekBar(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MusicSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    View view = View.inflate(getContext(), R.layout.view_music_seekbar, null);
    rlThumbBg = (RelativeLayout) view.findViewById(R.id.rl_thumb_bg);
    seekBar = (SeekBar) view.findViewById(R.id.seekBar);
    ivCircle = (ImageView) view.findViewById(R.id.iv_circle);
    addView(view);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    rlThumbBg.measure(0, 0);
    rlThumbBgHeight = rlThumbBg.getMeasuredHeight();
    rlThumbBgWidth = rlThumbBg.getMeasuredWidth();
    ivCircleHeight = ivCircle.getMeasuredHeight();
    ivCircleWidth = ivCircle.getMeasuredWidth();
    seekBarHeight = seekBar.getMeasuredHeight();
    seekBarWidth = seekBar.getMeasuredWidth();
//    Log.e(TAG, "onSizeChanged: rlThumbBgHeight="+rlThumbBgHeight+",rlThumbBgWidth="+rlThumbBgWidth+",ivCircleHeight="+ivCircleHeight+",ivCircleWidth="+ivCircleWidth );
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
//    Log.e(TAG, "onLayout: int l, int t, int r, int b="+l+"," +t+","+t+","+b);
    int progress=seekBar.getProgress();
    int max = seekBar.getMax();
    int margin = (int) (rlThumbBgWidth * (1 - progress * 1f / max));
    rlThumbBg.layout(0,0,r-margin,b);
    RelativeLayout.LayoutParams params = (LayoutParams) rlThumbBg.getLayoutParams();
    Log.e(TAG, "onProgressChanged:margin= " + margin + ",percent" + (progress * 1f / max));
    params.rightMargin = margin;
    params.width = rlThumbBgWidth;
    params.height = rlThumbBgHeight;
    rlThumbBg.setLayoutParams(params);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int progress=seekBar.getProgress();
    int max = seekBar.getMax();
    int margin = (int) (rlThumbBgWidth * (1 - progress * 1f / max));
    rlThumbBg.layout(0,0,rlThumbBgWidth-margin,rlThumbBgHeight);
  }


}
