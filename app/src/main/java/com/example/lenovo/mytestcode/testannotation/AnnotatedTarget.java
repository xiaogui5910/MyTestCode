package com.example.lenovo.mytestcode.testannotation;

/**
 * Created by ccg on 2017/11/20.
 * AnnotatedTarget用来标记我们定义的注解
 */

@TestAnnotation(value1 = {1,2,3,4},value2 = "类上String类型的注解参数")
public class AnnotatedTarget {
  @TestAnnotation(value1 = {5,6},value2 = "Filed1上的string类型注解参数")
  public String filed1;

  @TestAnnotation(value1 = {7,8},value2 = "Filed2上的string类型注解参数")
  public String filed2;

  @TestAnnotation(value1 = {9,10},value2 = "私有Filed3上String类型的注解参数")
  private String filed3;

  @TestAnnotation(value1 = 11)
  public static final int VALUE_FINAL=2;

  @TestAnnotation(value1 = 12)
  public void method1(){}

  @TestAnnotation(value1 = 13,value2 ="method2()私有方法注解string")
  private void method2(){}
}
