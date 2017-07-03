package com.example.view_texst;



/**
 * 存放一些比较杂的工具方法
 * @author lxj
 *
 */
public class CommonUtils {
	public static String getString(int resId){
		return a.context.getResources().getString(resId);
	}
	public static String[] getStringArray(int resId){
		return a.context.getResources().getStringArray(resId);
	}
	/**
	 * 获取dimens中的资源,但是获取的数值是将dp转换为px之后的值
	 * @param resId
	 * @return
	 */
	public static int getDimens(int resId){
		return a.context.getResources().getDimensionPixelSize(resId);
	}
}
