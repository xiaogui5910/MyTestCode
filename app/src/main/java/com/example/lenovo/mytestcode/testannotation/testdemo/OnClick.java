package com.example.lenovo.mytestcode.testannotation.testdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ccg on 2017/11/20.
 * 代替setOnClickListener的注解
 */
@Target(ElementType.METHOD) //标注目标为方法上
@Retention(RetentionPolicy.RUNTIME) //生命周期为运行时
public @interface OnClick {
  int[] value();  //用于保存控件id集
}
