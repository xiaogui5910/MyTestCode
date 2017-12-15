package com.example.lenovo.mytestcode.utils;

/**
 * Created by ccg on 2017/12/15.
 */

public class NdkTestUtils {

  public static native String getStringFromC();

  static {
    System.loadLibrary("NdkJniDemo");
  }
}
