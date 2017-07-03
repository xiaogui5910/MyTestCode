package com.example.lenovo.mytestcode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.mytestcode.bean.PlayProgress;
import com.example.lenovo.mytestcode.db.greendao.DaoMaster;
import com.example.lenovo.mytestcode.db.greendao.DaoSession;
import com.example.lenovo.mytestcode.db.greendao.PlayProgressDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by lenovo on 2017/4/21.
 */

public class PlayProgressDBManager {
  private final static String dbName = "silkwave";
  private static PlayProgressDBManager mInstance;
  private DaoMaster.DevOpenHelper openHelper;
  private Context context;

  private PlayProgressDBManager(Context context) {
    this.context = context;
    openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
  }

  public static PlayProgressDBManager getInstance(Context context) {
    if (mInstance == null) {
      synchronized (PlayProgressDBManager.class) {
        if (mInstance == null) {
          mInstance = new PlayProgressDBManager(context);
        }
      }
    }
    return mInstance;
  }

  /**
   * 获取可读数据库
   */private SQLiteDatabase getReadableDatabase() {
    if (openHelper == null) {
      openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }
    SQLiteDatabase db = openHelper.getReadableDatabase();
    return db;
  }

  /**
   * 获取可写数据库
   */private SQLiteDatabase getWritableDatabase() {
    if (openHelper == null) {
      openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }
    SQLiteDatabase db = openHelper.getWritableDatabase();
    return db;
  }

  /**
   * 获取播放进度数据库dao对象管理者
   * @return 可写dao对象
   */
  private PlayProgressDao getWritablePlayProgressDao() {
    DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
    DaoSession daoSession = daoMaster.newSession();
    return daoSession.getPlayProgressDao();
  }

  /**
   * 获取播放进度数据库dao对象管理者
   * @return 可读dao对象
   */
  private PlayProgressDao getReadablePlayProgressDao() {
    DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
    DaoSession daoSession = daoMaster.newSession();
    return daoSession.getPlayProgressDao();
  }

  /**
   * 插入一条记录
   * @param playProgress 进度记录
   */
  public void insertPlayProgress(PlayProgress playProgress){
    PlayProgressDao playProgressDao = getWritablePlayProgressDao();
    playProgressDao.insert(playProgress);
  }

  /**
   * 插入多条记录集合
   * @param list 记录集合
   */
  public void insertPlayProgressList(List<PlayProgress> list){
    if (list==null || list.isEmpty()){
      return;
    }
    PlayProgressDao playProgressDao = getWritablePlayProgressDao();
    playProgressDao.insertInTx(list);
  }

  /**
   * 删除一条记录
   * @param playProgress 进度记录
   */
  public void deletePlayProgress(PlayProgress playProgress){
    PlayProgressDao playProgressDao = getWritablePlayProgressDao();
    playProgressDao.delete(playProgress);
  }

  /**
   * 删除多条记录集合
   * @param list 记录集合
   */
  public void deletePlayProgressList(List<PlayProgress> list){
    PlayProgressDao playProgressDao = getWritablePlayProgressDao();
    playProgressDao.deleteInTx(list);
  }

  /**
   * 更新一条记录
   * @param playProgress 进度记录
   */
  public void updatePlayProgress(PlayProgress playProgress){
    PlayProgressDao playProgressDao = getWritablePlayProgressDao();
    playProgressDao.update(playProgress);
  }

  /**
   * 更新多条记录
   * @param list 记录集合
   */
  public void updatePlayProgressList(List<PlayProgress> list){
    PlayProgressDao playProgressDao = getWritablePlayProgressDao();
    playProgressDao.updateInTx(list);
  }

  /**
   * 查询播放进度列表
   * @return 播放进度列表
   */
  public List<PlayProgress> queryPlayProgressList(){
    PlayProgressDao playProgressDao = getReadablePlayProgressDao();
    QueryBuilder<PlayProgress> qb = playProgressDao.queryBuilder();
    List<PlayProgress> list = qb.list();
    return list;
  }

  /**
   * 查询指定的播放进度列表
   * @param playProgress 要查询的播放进度
   * @return 播放进度列表
   */
  public List<PlayProgress> queryPlayProgress(PlayProgress playProgress ){
    PlayProgressDao playProgressDao = getReadablePlayProgressDao();
    QueryBuilder<PlayProgress> qb = playProgressDao.queryBuilder();
    qb.where(PlayProgressDao.Properties.Program_number.gt(playProgress.getProgram_number()),
            PlayProgressDao.Properties.Event_id.gt(playProgress.getEvent_id()));
    List<PlayProgress> list = qb.list();
    return list;
  }
}
