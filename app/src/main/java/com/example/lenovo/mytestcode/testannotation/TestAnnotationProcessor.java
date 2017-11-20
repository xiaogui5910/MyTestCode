package com.example.lenovo.mytestcode.testannotation;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ccg on 2017/11/20.
 * TestAnnotation注解的处理器
 */

public class TestAnnotationProcessor {
  public static void process(Class clazz) {
    //类上注解
    Annotation clazzAnnotation = clazz.getAnnotation(TestAnnotation.class);
    if (clazzAnnotation != null) {
      Log.e("TestAnnotationProcessor", "类上的TestAnnotation参数值：value1 = " +
              Arrays.toString(((TestAnnotation) clazzAnnotation).value1()) + " , value2 = " +
              ((TestAnnotation) clazzAnnotation).value2());

    }

    //方法上注解
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
      if (annotation != null) {
        Log.e("TestAnnotationProcessor", "方法上的TestAnnotation参数值：value1 = " +
                Arrays.toString(((TestAnnotation) annotation).value1()) + " , value2 = " +
                ((TestAnnotation) annotation).value2());
      }
    }

    //field上注解
    Field[] declaredFields = clazz.getDeclaredFields();
    for (Field field : declaredFields) {
      TestAnnotation annotation = field.getAnnotation(TestAnnotation.class);
      if (annotation != null) {
        Log.e("TestAnnotationProcessor", "field上的TestAnnotation参数值：value1 = " +
                Arrays.toString(annotation.value1()) + " , value2 = " + annotation.value2());
      }
    }

  }
}
