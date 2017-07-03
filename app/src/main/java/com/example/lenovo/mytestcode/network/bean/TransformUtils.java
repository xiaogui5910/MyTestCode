package com.example.lenovo.mytestcode.network.bean;

import android.util.Log;

import com.example.lenovo.mytestcode.network.interf.RetrofitException;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/6/26.
 */

public class TransformUtils {

  private static final String TAG = "TransformUtils";

  /**
   * 线程转换
   * @param <T>
   * @return
   */
  public static <T> ObservableTransformer<T, T> defaultSchedulers() {
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  public static <T> ObservableTransformer<T, T> ioSchedulers() {
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
      }
    };
  }

  /**
   * 处理返回带data结果
   * @param <T>
   * @return
   */
  public static <T> ObservableTransformer<HttpResult<T>, T> handleResult() {
    return new ObservableTransformer<HttpResult<T>, T>() {
      @Override
      public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {
        return upstream.flatMap(new Function<HttpResult<T>, ObservableSource<T>>() {
          @Override
          public ObservableSource<T> apply(HttpResult<T> tHttpResult) throws Exception {
            if (tHttpResult.isSuccess()) {
              return createData1(tHttpResult.getData());
            } else {
              return Observable.error(new RetrofitException(tHttpResult.getCode()));
            }
          }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  /**
   * 处理返回类型没有data结果
   * @param <T>
   * @return
   */
  public static <T extends HttpResult> ObservableTransformer<T, T> handleDefaultResult() {
   return new ObservableTransformer<T, T>() {
     @Override
     public ObservableSource<T> apply(Observable<T> upstream) {
       return upstream.flatMap(new Function<T, ObservableSource<T>>() {
         @Override
         public ObservableSource<T> apply(T t) throws Exception {
           if ( t.isDefaultSuccess()){
             return createData1(t);
           }else {
             return Observable.error(new RetrofitException(t.getCode()));
           }
         }
       }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
     }
   };
  }


  private static <T> Observable<T> createData1(final T data) {
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> e) throws Exception {
        try {
          e.onNext(data);
          e.onComplete();
        } catch (Exception e1) {
          Log.e(TAG, "subscribe: e1=" +e1);
          e.onError(e1);
        }
      }
    });
  }


  public static <T> FlowableTransformer<HttpResult<T>, T> handleLiveResult() {
    return new FlowableTransformer<HttpResult<T>, T>() {
      @Override
      public Publisher<T> apply(Flowable<HttpResult<T>> upstream) {
        return upstream.flatMap(new Function<HttpResult<T>, Publisher<T>>() {
          @Override
          public Publisher<T> apply(HttpResult<T> tHttpResult) throws Exception {
            if (tHttpResult.isSuccess()) {
              return createData(tHttpResult.getData());
            } else {
              return Flowable.error(new RetrofitException(tHttpResult.getCode()));
            }
          }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  private static <T> Flowable<T> createData(final T data) {
    return Flowable.create(new FlowableOnSubscribe<T>() {
      @Override
      public void subscribe(FlowableEmitter<T> e) throws Exception {
        try {
          e.onNext(data);
          e.onComplete();
        } catch (Exception e1) {
          e.onError(e1);
        }
      }
    }, BackpressureStrategy.BUFFER);
  }
}
