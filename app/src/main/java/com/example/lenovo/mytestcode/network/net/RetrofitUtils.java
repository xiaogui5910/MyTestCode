package com.example.lenovo.mytestcode.network.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.lenovo.mytestcode.network.bean.EsgResponse;
import com.example.lenovo.mytestcode.network.bean.HttpResult;
import com.example.lenovo.mytestcode.network.bean.HttpResultFunc;
import com.example.lenovo.mytestcode.network.bean.MovieEntity;
import com.example.lenovo.mytestcode.network.interf.ApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/6/14.
 */

public class RetrofitUtils {
  private static final int DEFAULT_TIMEOUT = 5;
  private static final String TAG = "RetrofitUtils";

  private ApiService apiService;

  private OkHttpClient okHttpClient;

  public static String baseUrl = ApiService.BASE_URL;

  private static Context mContext;

  private RetrofitUtils(Context context) {
    this(context,null);
  }

  private RetrofitUtils(Context context,String url){
    if (TextUtils.isEmpty(url)){
      url=baseUrl;
    }

    okHttpClient = new OkHttpClient().newBuilder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
//            .cookieJar(new CookieManager(context))
//            .addInterceptor(new BaseInterceptor(mContext))
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    Log.e(TAG, "RetrofitUtils: url="+url );
    Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build();
     apiService = retrofit.create(ApiService.class);

  }

  public void addCookies(){
    okHttpClient.interceptors().add(new ReadCookiesInterceptor());
    okHttpClient.interceptors().add(new SaveCookiesInterceptor());
  }

  private static RetrofitUtils newInstance;

  private static class SingletonHolder {
    private static RetrofitUtils INSTANCE = new RetrofitUtils(
            mContext);
  }

  public static RetrofitUtils getInstance(Context context) {
    if (context != null) {
      mContext = context;
    }
    return SingletonHolder.INSTANCE;
  }

  public static RetrofitUtils getNewInstance(Context context, String url) {
    if (context != null) {
      mContext = context;
    }

    newInstance = new RetrofitUtils(context,url);
    return newInstance;
  }

  public void getData(Observer<EsgResponse> observer){

    apiService.getEsg()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer);

  }

  public void getTopMovie(int start,int count,Observer<MovieEntity> observer ){
    apiService.getMovie(start,count)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer);
  }
  public void getMyMovie(int start,int count,Observer<HttpResult<List<MovieEntity.SubjectsBean>>> observer ){
    apiService.getMyMovie(start,count)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer);
  }
  public void getMyMovie1(int start,int count,Observer<List<MovieEntity.SubjectsBean>> observer ){
    Observable<List<MovieEntity.SubjectsBean>> observable = apiService.getMyMovie(start, count)
            .map(new HttpResultFunc<List<MovieEntity.SubjectsBean>>());
    toSubscribe(observable,observer);
//            .subscribeOn(Schedulers.io())
//            .unsubscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(observer);
  }
  //添加线程管理并订阅
  private void toSubscribe(Observable o, Observer s){
    o.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s);
  }
}
