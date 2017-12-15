package com.example.lenovo.mytestcode.testDagger2;

import dagger.Component;

/**
 * Created by ccg on 2017/11/21.
 */
@Component(modules = CarModule.class)
public interface CarComponent {
  void inject(Car car);
}
