package com.example.lenovo.mytestcode.db;

import com.example.lenovo.mytestcode.application.MyTestCodeApp;
import com.example.lenovo.mytestcode.bean.Shop;
import com.example.lenovo.mytestcode.db.greendao.ShopDao;

import java.util.List;

/**
 * Created by lenovo on 2017/4/21.
 */

public class MyTestDao {
  /**
   * 添加数据，如果有重复则覆盖
   *
   * @param shop
   */
  public static void insertLove(Shop shop) {
    MyTestCodeApp.getDaoInstant().getShopDao().insertOrReplace(shop);
  }

  /**
   * 删除数据
   *
   * @param id
   */
  public static void deleteLove(long id) {
    MyTestCodeApp.getDaoInstant().getShopDao().deleteByKey(id);
  }

  /**
   * 更新数据
   *
   * @param shop
   */
  public static void updateLove(Shop shop) {
    MyTestCodeApp.getDaoInstant().getShopDao().update(shop);
  }

  /**
   * 查询条件为Type=TYPE_LOVE的数据
   *
   * @return
   */
  public static List<Shop> queryLove() {
    return MyTestCodeApp.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
  }

  /**
   * 查询全部数据
   */
  public static List<Shop> queryAll() {
    return MyTestCodeApp.getDaoInstant().getShopDao().loadAll();
  }

}
