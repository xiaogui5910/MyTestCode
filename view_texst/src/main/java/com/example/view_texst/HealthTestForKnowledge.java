package com.example.view_texst;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class HealthTestForKnowledge extends View {

	private int measureWidth;
	private int measureHeigth;
	private Paint paint;
	private String[] rowCharactor = { "60", "80", "120", "140", "180" };
	private float bottomLine;
	private float midLine;
	private float averageWidth;
	private ArrayList<DrugUseTest> al;
	private float downX;
	private float downY;
	private Scroller scroller;
	private float xPosition;
	private float baseX;
	float totalDix = 0;
	float lastPosition = 0;


	public HealthTestForKnowledge(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initePaint();
	}

	public HealthTestForKnowledge(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public HealthTestForKnowledge(Context context) {
		this(context, null);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		WindowManager manager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm=new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		int width2=dm.widthPixels;
		int height2=dm.heightPixels;
		
		measureHeigth = height2;
		measureWidth = MeasureSpec.getSize(widthMeasureSpec);
//		measureHeigth = MeasureSpec.getSize(heightMeasureSpec);
		midLine = measureWidth / 2;
		averageWidth = measureWidth / 8;
		baseX = midLine - measureHeigth / 80;
		System.out.println("measureHeigth"+measureHeigth);
		System.out.println("onMeasure....   afw");
	}

	private void initePaint() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.parseColor("#1698CC"));
		scroller = new Scroller(getContext());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBackground(canvas);
		drawRow(canvas);
		drawAbscissa(canvas);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			downX = event.getX();
			downY = event.getY();
			if (downY > (bottomLine+measureHeigth/ 20)) {
				return super.onTouchEvent(event);
			}

			break;
		case MotionEvent.ACTION_MOVE:


			float moveX = event.getX();
			float dix = moveX -downX;
			 totalDix = dix + totalDix;



			if (dix < 0 && baseX <= midLine - measureHeigth / 80) {
				return super.onTouchEvent(event);
			}
			if (lastPosition > averageWidth && dix > 0) {
				return super.onTouchEvent(event);
			}
			baseX = baseX + dix;
			downX = moveX;
//			System.out.println("dix"+dix);
//			System.out.println("basex:"+baseX);
//			System.out.println("baseX" +baseX);
			
//			move();
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		}
		invalidate();
		return true;
	}

	/**
	 * 重新封装系统的scrollTo 由于移动只是在水平方向 可以把scrollTo的第二个参数写死为0 scrollTo 默认处理传整数会向左
	 * 在这里去反
	 * 
	 * @param x
	 */
	public void scrollTo(int x) {
		super.scrollTo(-(int) x, 0);
	}

	private int MygetScollX() {
		return -getScrollX();
	}
	@Override
	public void computeScroll() {
		if(scroller.computeScrollOffset()){
			int currX = scroller.getCurrX();
//			//用计算的值开始去移动
			scrollTo(currX);
			invalidate();
		}
	}





	/**
	 * 画横坐标
	 * 
	 * @param canvas
	 */
	private void drawAbscissa(Canvas canvas) {
		String currentTime = getCurrentTime();
		int month = Integer.parseInt(currentTime.substring(5, 7));
		int day = Integer.parseInt(currentTime.substring(8, 10));
		int currentPosition = getCurrentPosition(month, day);
		if (currentPosition == -1) {
			return;
		}
		// System.out.println("m" +month+",d"+day);
		String text = month + "/" + day;
		
		
		float y = bottomLine + measureHeigth / 40;
		paint.setColor(Color.parseColor("#3A3A3A"));
		paint.setTextSize(20);
		// System.out.println("aa" +R.dimen.BloodGlucoseXText);
		canvas.drawText(text, baseX, y, paint);
		for (int i = currentPosition; i >= 0; i--) {
			text = al.get(i).getDate() ;
			xPosition = baseX - (currentPosition - i) * averageWidth;
			if (i == 1) {
				lastPosition = xPosition;
				System.out.println("lastPosition..."+lastPosition);
			}
			canvas.drawText(text, xPosition, y, paint);
		}

	}

	/**
	 * 获取当前时间的位置
	 * 
	 * @param month
	 * @param day
	 * @return
	 */
	private int getCurrentPosition(int month, int day) {
		int currentPosition = -1;
		for (int i = 0; i < al.size(); i++) {
//			if (abscissaData.get(i).month == month
//					&& abscissaData.get(i).dayOfMonth == day) {
//				currentPosition = i;
//				return currentPosition;
//			}
		}

//		System.out.println("aa:" + currentPosition);
		return currentPosition;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	private String getCurrentTime() {
		long currentTimeMillis = System.currentTimeMillis();
		// System.out.println("currentTimeMillis ," + currentTimeMillis);
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd    hh:mm:ss");
		String format = sDateFormat.format(new Date(currentTimeMillis));
		// System.out.println("format," + format);
		return format;
	}

	// 画行
	private void drawRow(Canvas canvas) {
		paint.setColor(Color.parseColor("#39C8FF"));
		paint.setStrokeWidth(1);
		float averageHeight = measureHeigth / 18;
		float startX = 0;
		float stopX = measureWidth;
		for (int i = 0; i < 5; i++) {
			float startY = measureHeigth / 3 - averageHeight * (i + 1);
			float stopY = startY;
			canvas.drawLine(startX, startY, stopX, stopY, paint);
			drawCharactor(canvas, averageHeight, i, startY);
		}

	}

	/**
	 * 画纵坐标的字
	 * 
	 * @param canvas
	 * @param averageHeight
	 * @param i
	 * @param startY
	 */
	private void drawCharactor(Canvas canvas, float averageHeight, int i,
			float startY) {
		paint.setColor(Color.parseColor("#ffffff"));
		// int bloodglucoseytext = R.dimen.BloodGlucoseYText;
		paint.setTextSize(22);
		float x = measureWidth - measureWidth / 10;
		float y = startY - averageHeight / 10;
		switch (i) {
		case 0:
			canvas.drawText(rowCharactor[i], x, y, paint);
			break;
		case 1:
			canvas.drawText(rowCharactor[i], x, y, paint);
			break;
		case 2:
			canvas.drawText(rowCharactor[i], x, y, paint);
			break;
		case 3:
			canvas.drawText(rowCharactor[i], x, y, paint);
			break;
		case 4:
			canvas.drawText(rowCharactor[i], x, y, paint);
			break;

		}
	}

	/**
	 * 画背景
	 * 
	 * @param canvas
	 */
	private void drawBackground(Canvas canvas) {
		float left = 0;
		float top = 0;
		float right = measureWidth;
		bottomLine = measureHeigth / 3;
		paint.setColor(Color.parseColor("#1698CC"));
		canvas.drawRect(left, top, right, bottomLine, paint);
		paint.setColor(Color.parseColor("#EAEAEA"));
		canvas.drawRect(left, bottomLine, right, bottomLine + measureHeigth
				/ 20, paint);
		paint.setColor(Color.parseColor("#ffffff"));
		paint.setStrokeWidth(3);
		canvas.drawLine(measureWidth / 2, bottomLine, midLine,
				measureHeigth / 9, paint);
		// System.out.println("画背景");
	}

	/**
	 * 设置数据
	 * 
	 * @param data
	 */
	public void setData(ArrayList<DrugUseTest> data) {
		al = data;
	}


}
