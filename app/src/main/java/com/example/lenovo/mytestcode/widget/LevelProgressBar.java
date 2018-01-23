package com.example.lenovo.mytestcode.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.lenovo.mytestcode.R;

/**
 * Created by ccg on 2018/1/23.
 */

public class LevelProgressBar extends ProgressBar {
  private static final int PROGRESS = 1;
  private String[] levelTexts;
  private int levels;
  private int currentLevel;
  private int animInterval;
  private int levelTextSize;
  private int levelTextChooseColor;
  private int levelTextUnChooseColor;
  private int progressBgColor;
  private int progressStartColor;
  private int progressEndColor;
  private int progressHeight;
  private int progressSpace;
  private Paint paint;
  private int textHeight;
  private int totalWidth;
  private int targetProgress;

  public LevelProgressBar(Context context) {
    this(context, null);
  }

  public LevelProgressBar(Context context, AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public LevelProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      int progress = getProgress();
      if (progress < targetProgress) {
        setProgress(++progress);
        handler.sendEmptyMessageDelayed(PROGRESS, animInterval);
      } else if (progress > targetProgress) {
        setProgress(--progress);
        handler.sendEmptyMessageDelayed(PROGRESS, animInterval);
      } else {
        handler.removeMessages(PROGRESS);
      }

    }
  };

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LevelProgressBar);
    levelTextSize = (int) ta.getDimension(R.styleable.LevelProgressBar_levelTextSize, dp2px(15));
    levelTextChooseColor = ta.getColor(R.styleable.LevelProgressBar_levelTextChooseColor, 0X333333);
    levelTextUnChooseColor = ta.getColor(R.styleable.LevelProgressBar_levelTextUnChooseColor, 0X000000);
    progressBgColor = ta.getColor(R.styleable.LevelProgressBar_progressBgColor, 0X000000);
    progressStartColor = ta.getColor(R.styleable.LevelProgressBar_progressStartColor, 0X00FF00);
    progressEndColor = ta.getColor(R.styleable.LevelProgressBar_progressEndColor, 0XCCFFCC);
    progressHeight = (int) ta.getDimension(R.styleable.LevelProgressBar_progressHeight, dp2px(20));
    progressSpace = (int) ta.getDimension(R.styleable.LevelProgressBar_progressSpace, dp2px(10));
    ta.recycle();

    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(levelTextSize);
    paint.setColor(levelTextUnChooseColor);
  }

  private int dp2px(int dp) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
  }

  @Override
  protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    if (heightMode != MeasureSpec.EXACTLY) {
      textHeight = (int) (paint.descent() - paint.ascent());
      height = getPaddingTop() + getPaddingBottom() + textHeight+progressHeight +progressSpace;
    }
    setMeasuredDimension(width, height);
    totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }

  @Override
  protected synchronized void onDraw(Canvas canvas) {
//    super.onDraw(canvas);
    canvas.save();
    canvas.translate(getPaddingLeft(), getPaddingTop());
    //绘制等级文字
    for (int i = 0; i < levelTexts.length; i++) {
      int textWidth = (int) paint.measureText(levelTexts[i]);
      paint.setColor(levelTextUnChooseColor);
      paint.setTextSize(levelTextSize);
      if (getProgress() == targetProgress && currentLevel >= 1 && currentLevel <= levels && i == currentLevel - 1) {
        paint.setColor(levelTextChooseColor);
      }
      canvas.drawText(levelTexts[i], totalWidth / levels * (i + 1) - textWidth, textHeight, paint);
    }

    //绘制进度条底部
    int lineY = textHeight + progressHeight / 2 + progressSpace;
    paint.setColor(progressBgColor);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeWidth(progressHeight);
    canvas.drawLine(0 + progressHeight / 2, lineY, totalWidth - progressHeight / 2, lineY, paint);

    //绘制进度条
    int reachedPartEnd = (int) (getProgress() * 1f / getMax() * totalWidth);
    if (reachedPartEnd > 0) {
      paint.setStrokeCap(Paint.Cap.ROUND);
      Shader shader = new LinearGradient(0, lineY, getWidth(), lineY,
              progressStartColor, progressEndColor, Shader.TileMode.REPEAT);
      paint.setShader(shader);
      int accurateEnd = reachedPartEnd - progressHeight / 2;
      int accurateStart = 0 + progressHeight / 2;
      if (accurateEnd > accurateStart) {
        canvas.drawLine(accurateStart, lineY, accurateEnd, lineY, paint);
      } else {
        canvas.drawLine(accurateStart, lineY, accurateStart, lineY, paint);
      }
      paint.setShader(null);
    }
    canvas.restore();
  }

  public String[] getLevelTexts() {
    return levelTexts;
  }

  public void setLevelTexts(String[] levelTexts) {
    this.levelTexts = levelTexts;
  }

  public int getLevels() {
    return levels;
  }

  public void setLevels(int levels) {
    this.levels = levels;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(int currentLevel) {
    this.currentLevel = currentLevel;
    targetProgress = (int) (currentLevel * 1f / levels * getMax());
  }

  public int getAnimInterval() {
    return animInterval;

  }

  public void setAnimInterval(int animInterval) {
    this.animInterval = animInterval;
    handler.sendEmptyMessage(PROGRESS);
  }

  public int getLevelTextSize() {
    return levelTextSize;
  }

  public void setLevelTextSize(int levelTextSize) {
    this.levelTextSize = levelTextSize;
  }

  public int getLevelTextChooseColor() {
    return levelTextChooseColor;
  }

  public void setLevelTextChooseColor(int levelTextChooseColor) {
    this.levelTextChooseColor = levelTextChooseColor;
  }

  public int getLevelTextUnChooseColor() {
    return levelTextUnChooseColor;
  }

  public void setLevelTextUnChooseColor(int levelTextUnChooseColor) {
    this.levelTextUnChooseColor = levelTextUnChooseColor;
  }

  public int getProgressBgColor() {
    return progressBgColor;
  }

  public void setProgressBgColor(int progressBgColor) {
    this.progressBgColor = progressBgColor;
  }

  public int getProgressStartColor() {
    return progressStartColor;
  }

  public void setProgressStartColor(int progressStartColor) {
    this.progressStartColor = progressStartColor;
  }

  public int getProgressEndColor() {
    return progressEndColor;
  }

  public void setProgressEndColor(int progressEndColor) {
    this.progressEndColor = progressEndColor;
  }

  public int getProgressHeight() {
    return progressHeight;
  }

  public void setProgressHeight(int progressHeight) {
    this.progressHeight = progressHeight;
  }

  public int getProgressSpace() {
    return progressSpace;
  }

  public void setProgressSpace(int progressSpace) {
    this.progressSpace = progressSpace;
  }
}
