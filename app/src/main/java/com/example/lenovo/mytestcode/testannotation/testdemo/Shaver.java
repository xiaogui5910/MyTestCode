package com.example.lenovo.mytestcode.testannotation.testdemo;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ccg on 2017/11/20.
 * 注入接口
 */

public class Shaver {
  public static void bind(Activity activity) {
    try {
      bindContentView(activity);
      bindStringRes(activity);
      bindViews(activity);
      bindClicks(activity);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void bindContentView(Activity activity) {
    Class<? extends Activity> aClass = activity.getClass();
    ContentView annotation = aClass.getAnnotation(ContentView.class);
    if (annotation != null) {
      int layoutId = annotation.value();
      activity.setContentView(layoutId);

      //也可用反射实现，但效率低且麻烦
      try {
        aClass.getMethod("setContentView", int.class).invoke(activity, layoutId);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
    }
  }

  private static void bindStringRes(Activity activity) {
    Class<? extends Activity> aClass = activity.getClass();
    Field[] fields = aClass.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(StringRes.class)) {
        StringRes annotation = field.getAnnotation(StringRes.class);
        int stringId = annotation.value();
        String stringValue = activity.getString(stringId);
        field.setAccessible(true);
        try {
          field.set(activity, stringValue);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static void bindViews(Activity activity) {
    Class<? extends Activity> aClass = activity.getClass();
    Field[] declaredFields = aClass.getDeclaredFields();
    for (Field field : declaredFields) {
      if (field.isAnnotationPresent(Bind.class)) {
        Bind bind = field.getAnnotation(Bind.class);
        int viewId = bind.value();
        View view = activity.findViewById(viewId);
        field.setAccessible(true);
        try {
          field.set(activity, view);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static void bindClicks(final Activity activity) {
    Class<? extends Activity> aClass = activity.getClass();
    Method[] declaredMethods = aClass.getDeclaredMethods();
    for (final Method method:declaredMethods) {
      if (method.isAnnotationPresent(OnClick.class)){
        OnClick annotation = method.getAnnotation(OnClick.class);
        int[] viewIds = annotation.value();
        for (int viewId:viewIds) {
          final View view = activity.findViewById(viewId);
          if (view!=null){
            view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                method.setAccessible(true);
                try {
                  method.invoke(activity,view);
                } catch (IllegalAccessException e) {
                  e.printStackTrace();
                } catch (InvocationTargetException e) {
                  e.printStackTrace();
                }
              }
            });
          }
        }
      }
    }
  }
}
