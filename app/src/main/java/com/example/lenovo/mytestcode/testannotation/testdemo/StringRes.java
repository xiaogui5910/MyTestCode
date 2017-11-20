package com.example.lenovo.mytestcode.testannotation.testdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ccg on 2017/11/20.
 * 代替getResource().getString(R.string.xx)
 */
@Target(ElementType.FIELD) //标注目标为成员变量
@Retention(RetentionPolicy.RUNTIME) //生命周期为运行时
public @interface StringRes {
  int value();  //用于保存string的id
}
