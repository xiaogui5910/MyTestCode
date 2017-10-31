package com.example.lenovo.mytestcode.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.mytestcode.BuildConfig;
import com.example.lenovo.mytestcode.db.greendao.DaoMaster;
import com.example.lenovo.mytestcode.db.greendao.DaoSession;
import com.example.lenovo.mytestcode.utils.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by lenovo on 2017/3/22.
 */

public class MyTestCodeApp extends Application {
  private static final String MY_TAG = "MyTestCode";
  private static DaoSession daoSession;
  public static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    this.context =this;
    FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
            .methodCount(2)         // (Optional) How many method line to show. Default 2
//            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//            .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag(MY_TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build();
//    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//    Logger.addLogAdapter(new AndroidLogAdapter());
    Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
      @Override
      public boolean isLoggable(int priority, String tag) {
        return BuildConfig.DEBUG;
      }
    });

    //TODO: More information will be added later
//    Logger.addLogAdapter(new DiskLogAdapter());
//    FormatStrategy formatStrategy1 = CsvFormatStrategy.newBuilder()
//            .tag("custom")
//            .build();
//
//    Logger.addLogAdapter(new DiskLogAdapter(formatStrategy1));

    //配置数据库
    setupDatabase();
    Utils.init(this);
  }

  /**
   * 配置数据库
   */
  private void setupDatabase() {
    //创建数据库shop.db"
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
    //获取可写数据库
    SQLiteDatabase db = helper.getWritableDatabase();
    //获取数据库对象
    DaoMaster daoMaster = new DaoMaster(db);
    //获取Dao对象管理者
    daoSession = daoMaster.newSession();
  }

  public static DaoSession getDaoInstant() {
    return daoSession;
  }
}
