package com.example.lenovo.mytestcode.testannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ccg on 2017/11/20.
 * 定义一个注解TestAnnotation
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD}) //声明作用域
@Retention(RetentionPolicy.RUNTIME) //声明生命周期
public @interface TestAnnotation {
  int[] value1();

  String value2() default "default string";
}
