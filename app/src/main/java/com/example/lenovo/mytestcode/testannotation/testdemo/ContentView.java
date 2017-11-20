package com.example.lenovo.mytestcode.testannotation.testdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ccg on 2017/11/20.
 * 代替setContentView的注解
 */
@Target(ElementType.TYPE)  //标注目标为类前
@Retention(RetentionPolicy.RUNTIME)   //生命周期为运行时
public @interface ContentView {
  int value();  //用于保存layoutId
}
