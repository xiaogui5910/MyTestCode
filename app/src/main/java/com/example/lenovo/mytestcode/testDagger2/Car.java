package com.example.lenovo.mytestcode.testDagger2;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by ccg on 2017/11/21.
 */

public class Car {
//  private Engine engine;
//  private Seat seat;
//  private Wheel wheel;
//  public Car() {
//    engine = new Engine();
//    seat = new Seat();
//    wheel = new Wheel();
//    Log.d(Config.TAG, "new Car()");
//  }

  @Inject
  Engine engine;
  @Inject
  Seat seat;
  @Inject
  Wheel wheel;
  public Car(){
    DaggerCarComponent
            .builder()
            .carModule(new CarModule())
            .build()
            .inject(this);
    Log.d(Config.TAG, "new Car()");
  }
}
