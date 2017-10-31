package com.example.lenovo.mytestcode.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/** 
 * 常用单位转换的辅助类 
 *  
 *  
 *  
 */  
public class DensityUtils  
{  
    private DensityUtils()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
  
    /** 
     * dp转px 
     *  
     * @param context 
     * @param dpVal
     * @return 
     */  
    public static int dp2px(Context context, float dpVal)  
    {  
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,  
                dpVal, context.getResources().getDisplayMetrics());  
    }  
  
    /** 
     * sp转px 
     *  
     * @param context 
     * @param spVal
     * @return 
     */  
    public static int sp2px(Context context, float spVal)  
    {  
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,  
                spVal, context.getResources().getDisplayMetrics());  
    }  
  
    /** 
     * px转dp 
     *  
     * @param context 
     * @param pxVal 
     * @return 
     */  
    public static float px2dp(Context context, float pxVal)  
    {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (pxVal / scale);  
    }  
  
    /** 
     * px转sp 
     *  
     * @param context
     * @param pxVal 
     * @return 
     */  
    public static float px2sp(Context context, float pxVal)  
    {  
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);  
    }

  /**
   * 测量 View
   *
   * @param measureSpec
   * @param defaultSize View 的默认大小
   * @return
   */
  public static int measure(int measureSpec, int defaultSize) {
    int result = defaultSize;
    int specMode = View.MeasureSpec.getMode(measureSpec);
    int specSize = View.MeasureSpec.getSize(measureSpec);

    if (specMode == View.MeasureSpec.EXACTLY) {
      result = specSize;
    } else if (specMode == View.MeasureSpec.AT_MOST) {
      result = Math.min(result, specSize);
    }
    return result;
  }

  /**
   * 反转数组
   *
   * @param arrays
   * @param <T>
   * @return
   */
  public static <T> T[] reverse(T[] arrays) {
    if (arrays == null) {
      return null;
    }
    int length = arrays.length;
    for (int i = 0; i < length / 2; i++) {
      T t = arrays[i];
      arrays[i] = arrays[length - i - 1];
      arrays[length - i - 1] = t;
    }
    return arrays;
  }


}  