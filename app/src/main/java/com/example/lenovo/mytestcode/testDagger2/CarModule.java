package com.example.lenovo.mytestcode.testDagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ccg on 2017/11/21.
 */
@Module
public class CarModule {
  @Provides
  public Engine provideEngine(){
    return new Engine();
  }
  @Provides
  public Seat provideSeat(){
    return new Seat();
  }
  @Provides
  public Wheel provideWheel(){
    return new Wheel();
  }
}
