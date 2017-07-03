package com.example.lenovo.mytestcode.network.interf;

import com.example.lenovo.mytestcode.network.bean.EsgResponse;
import com.example.lenovo.mytestcode.network.bean.HttpResult;
import com.example.lenovo.mytestcode.network.bean.MovieEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2017/6/14.
 */

public interface ApiService {
  public static final String BASE_URL = "http://fes.gvmedia.com.cn/";

  @GET("v1/esg")
  Observable<EsgResponse> getEsg();

  @GET("top250")
  Observable<MovieEntity> getMovie(@Query("start") int start,@Query("count") int count);

  @GET("top250")
  Observable<HttpResult<List<MovieEntity.SubjectsBean>>> getMyMovie(@Query("start") int start, @Query("count") int count);


  @GET("top250")
  Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
