package com.example.lenovo.mytestcode.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.mytestcode.db.greendao.DaoMaster;
import com.example.lenovo.mytestcode.db.greendao.DaoSession;
import com.example.lenovo.mytestcode.utils.AndroidLogAdapter;
import com.example.lenovo.mytestcode.utils.Utils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by lenovo on 2017/3/22.
 */

public class MyTestCodeApp extends Application {
  private static final String YOUR_TAG = "MyTestCode";
  private static DaoSession daoSession;

  @Override
  public void onCreate() {
    super.onCreate();

    Logger
            .init(YOUR_TAG)                 // default PRETTYLOGGER or use just init()
            .methodCount(3)                 // default 2
            .hideThreadInfo()               // default shown
            .logLevel(LogLevel.NONE)        // default LogLevel.FULL
            .methodOffset(2)                // default 0
            .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter

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
