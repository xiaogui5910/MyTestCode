package com.example.lenovo.mytestcode.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.mytestcode.R;
import com.example.lenovo.mytestcode.network.bean.EsgResponse;
import com.example.lenovo.mytestcode.network.bean.HttpResult;
import com.example.lenovo.mytestcode.network.bean.MovieEntity;
import com.example.lenovo.mytestcode.network.interf.ApiService;
import com.example.lenovo.mytestcode.network.interf.SubscriberOnNextListener;
import com.example.lenovo.mytestcode.network.net.RetrofitUtils;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lenovo.mytestcode.R.id.tv_get_code;

public class RxJavaTestActivity extends AppCompatActivity {

  private static final String TAG = "RxJavaTestActivity";
  @Bind(R.id.start)
  Button start;
  @Bind(R.id.request)
  Button request;
  @Bind(R.id.tv_movie)
  TextView tvMovie;
  @Bind(R.id.btn_movie)
  Button btnMovie;
  @Bind(R.id.et_code)
  EditText etCode;
  @Bind(tv_get_code)
  TextView tvGetCode;
  private Subscription mSubscription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rx_java_test);
    ButterKnife.bind(this);
    initView();
    initData();
  }

  private void initView() {

  }

  private void initData() {
    //创建上游Observable
    final Observable<Integer> observable = createObservable();


    //创建下游Observer
    Observer<Integer> observer = createObserver();

    Consumer<Integer> consumer = createConsumer();


    //建立连接
    //test1 普通方法1
//    observable.subscribe(observer);

    //test2 普通方法2
//    observable.subscribe(new Consumer<Integer>() {
//      @Override
//      public void accept(Integer integer) throws Exception {
//        Log.e(TAG, "accept: integer="+integer );
//      }
//    });

    //test3 发送和接收在不同线程
//    observable.subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(consumer);

    //test4 每调用一次observeOn() 线程便会切换一次
//    test4(observable, consumer);

    //test5 网络请求
//    test5();

    //test6 读取数据库
//    test6();

    //test7 map操作符 对上游发送的每一个事件应用一个函数, 使得每一个事件都按照指定的函数去变化
//    test7();

    //test8 flatMap操作符 将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
    //  flatMap并不保证事件的顺序,如果需要保证顺序则需要使用concatMap
//    test8();

    //test9 先注册，注册成功后自动登录
//    test9();

    //test10 zip操作符 Zip通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据
    //上游发送运行在同一个线程里, 同一个线程里执行代码肯定有先后顺序
//    test10();
    //上游发送运行在不同线程里
//    test11();

    //实践 比如一个界面需要展示用户的一些信息, 而这些信息分别要从两个服务器接口中获取, 而只有当两个都获取到了之后才能进行展示, 这个时候就可以用Zip了
    //test12
//    test12();

    //test13 filter过滤
//    test13();

    //test14 sample操作符, 简单做个介绍, 这个操作符每隔指定的时间就从上游中取出一个事件发送给下游. 这里我们让它每隔2秒取一个事件给下游
//    test14();

    //test15 解决zip其中一个上游发送过快问题
//    test15();

    //Flowable 和 subscriber配合使用 同步测试
//    test16();

    //test17 异步测试 响应式拉取
//    test17();

    //test18   Flowable的源码中就有这个buffersize的大小定义128
//    test18();

    //test19 Flowable的interval操作符发送Long型的事件, 从0开始, 每隔指定的时间就把数字加1并发送出来,
//    test19();

    //RxJava2 和 Retrofit配合实践
//    test20();

//    test21();

//    test23();

    //test24 just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据。通过just( )方式 直接触发onNext()，just中传递的参数将直接在Observer的onNext()方法中接收到。
//    test24();

    //test25 fromArray()
//    test25();

    //test26() 使用fromIterable()，遍历集合，发送每个item。相当于多次回调onNext()方法，每次传入一个item
    // Collection接口是Iterable接口的子接口，所以所有Collection接口的实现类都可以作为Iterable对象直接传入fromIterable()方法
//    test26();

    //test27 defer() 当观察者订阅时，才创建Observable，并且针对每个观察者创建都是一个新的Observable。以何种方式创建这个Observable对象，当满足回调条件后，就会进行相应的回调
//    test27();

    //test28 interval( )方式 创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。即按照固定2秒一次调用onNext()方法
    //实践验证码倒计时
//    test28();

    //test29 filter()操作符根据test()方法中，根据自己想过滤的数据加入相应的逻辑判断，返回true则表示数据满足条件，
    // 返回false则表示数据需要被过滤。最后过滤出的数据将加入到新的Observable对象中，方便传递给Observer想要的数据形式。
    test29();

    //test30 take()操作符：输出最多指定数量的结果。
    test30();

    //doOnNext()允许我们在每次输出一个元素之前做一些额外的事情。
  }

  private void test30() {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i+"个");
    }
    Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
      @Override
      public ObservableSource<?> apply(List<String> strings) throws Exception {
        return Observable.fromIterable(strings);
      }
    }).take(5).subscribe(new Consumer<Object>() {
      @Override
      public void accept(Object s) throws Exception {
        System.out.println((String)s);
      }
    });
  }

  private void test29() {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i+"个");
    }
    Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
      @Override
      public ObservableSource<?> apply(List<String> strings) throws Exception {
        return Observable.fromIterable(strings);
      }
    }).filter(new Predicate<Object>() {
      @Override
      public boolean test(Object s) throws Exception {
        String newStr = (String) s;
        if (newStr.charAt(5) - '0' > 5) {
          return true;
        }
        return false;
      }
    }).subscribe(new Consumer<Object>() {
      @Override
      public void accept(Object o) throws Exception {
        System.out.println((String)o);
      }
    });
  }

  private void test28() {
    final long count = 10;
    Observable.interval(0, 1, TimeUnit.SECONDS)
            .take(count + 1)
            .map(new Function<Long, Long>() {
              @Override
              public Long apply(@NonNull Long aLong) throws Exception {
//                Log.e(TAG, "apply: aLong="+aLong );
                return count - aLong;
              }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
              @Override
              public void accept(Disposable disposable) throws Exception {
                tvGetCode.setEnabled(false);
                tvGetCode.setTextColor(Color.BLACK);
              }
            })
            .subscribe(new Observer<Long>() {
              @Override
              public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
              }

              @Override
              public void onNext(Long value) {
                Log.e(TAG, "onNext:value= " + value);
                tvGetCode.setText(value + "秒后重试");
              }

              @Override
              public void onError(Throwable e) {
                Log.e(TAG, "onError: e=" + e.toString());
              }

              @Override
              public void onComplete() {
                tvGetCode.setEnabled(true);
                tvGetCode.setText("获取验证码");
                tvGetCode.setTextColor(Color.RED);
                Log.e(TAG, "onComplete: ");
              }
            });
  }

  private void test27() {
    Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
      @Override
      public ObservableSource<? extends String> call() throws Exception {
        return Observable.just("hello");
      }
    });
  }

  private void test26() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    Flowable.fromIterable(list)
            .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Exception {
                Log.e(TAG, "accept: fromIterable---->item=" + integer);
              }
            });
  }

  private void test25() {
    Flowable.fromArray(1, 3, 5, 7, 9)
            .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Exception {
                Log.e(TAG, "accept: fromArray---->i=" + integer);
              }
            });
  }

  private void test24() {
    Flowable.just("hello!")
            .subscribe(new Consumer<String>() {
              @Override
              public void accept(String s) throws Exception {
                Log.e(TAG, "accept: just---->s=" + s);
              }
            });
  }

  private void test23() {
    subscriberOnNextListener = new SubscriberOnNextListener<List<MovieEntity.SubjectsBean>>() {
      @Override
      public void onNext(List<MovieEntity.SubjectsBean> subjectsBeen) {
//        tvMovie.setText(subjectsBeen.toString());
      }
    };
  }

  SubscriberOnNextListener<List<MovieEntity.SubjectsBean>> subscriberOnNextListener;

  private void test21() {
    //进行网络请求
    String baseUrl = "https://api.douban.com/v2/movie/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService movieService = retrofit.create(ApiService.class);
    Call<MovieEntity> call = movieService.getTopMovie(0, 10);
    call.enqueue(new Callback<MovieEntity>() {
      @Override
      public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
        tvMovie.setText(response.body().toString());
      }

      @Override
      public void onFailure(Call<MovieEntity> call, Throwable t) {
        tvMovie.setText(t.getMessage());
      }
    });
  }

  private void test20() {
    String url = "https://api.douban.com/v2/movie/";
    RetrofitUtils.getNewInstance(this, url)
            .getTopMovie(0, 10, new Observer<MovieEntity>() {
              @Override
              public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
              }

              @Override
              public void onNext(MovieEntity value) {
                tvMovie.setText(value.toString());
              }

              @Override
              public void onError(Throwable e) {
                Log.e(TAG, "onError: e=" + e.toString());
              }

              @Override
              public void onComplete() {
                Log.e(TAG, "onComplete: ");
              }
            });
  }

  private void test22() {
    String url = "https://api.douban.com/v2/movie/";
    RetrofitUtils.getNewInstance(this, url)
            .getMyMovie(0, 10, new Observer<HttpResult<List<MovieEntity.SubjectsBean>>>() {
              @Override
              public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
              }

              @Override
              public void onNext(HttpResult<List<MovieEntity.SubjectsBean>> value) {
                tvMovie.setText(value.toString());
              }

              @Override
              public void onError(Throwable e) {
                Log.e(TAG, "onError: e=" + e.toString());
              }

              @Override
              public void onComplete() {
                Log.e(TAG, "onComplete: ");
              }
            });
  }

  private void test19() {
    Flowable.interval(1, TimeUnit.MICROSECONDS)
            .onBackpressureDrop()  //加上背压策略
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Long>() {
              @Override
              public void onSubscribe(Subscription s) {
                Log.e(TAG, "onSubscribe");
                mSubscription = s;
                s.request(Long.MAX_VALUE);
              }

              @Override
              public void onNext(Long aLong) {
                Log.e(TAG, "onNext: " + aLong);
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }

              @Override
              public void onError(Throwable t) {
                Log.e(TAG, "onError: ", t);
              }

              @Override
              public void onComplete() {
                Log.e(TAG, "onComplete");
              }
            });
  }

  private void test18() {
    Flowable.create(new FlowableOnSubscribe<Integer>() {
      @Override
      public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
        for (int i = 0; i < 128; i++) {
          Log.e(TAG, "emit " + i);
          emitter.onNext(i);
        }
      }
    }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Integer>() {

              @Override
              public void onSubscribe(Subscription s) {
                Log.e(TAG, "onSubscribe");
                mSubscription = s;
              }

              @Override
              public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
              }

              @Override
              public void onError(Throwable t) {
                Log.e(TAG, "onError: ", t);
              }

              @Override
              public void onComplete() {
                Log.e(TAG, "onComplete");
              }
            });
  }

  private void test17() {
    Flowable.create(new FlowableOnSubscribe<Integer>() {
      @Override
      public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
        Log.e(TAG, "emit 1");
        emitter.onNext(1);
        Log.e(TAG, "emit 2");
        emitter.onNext(2);
        Log.e(TAG, "emit 3");
        emitter.onNext(3);
        Log.e(TAG, "emit complete");
        emitter.onComplete();
      }
    }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Integer>() {

              @Override
              public void onSubscribe(Subscription s) {
                Log.e(TAG, "onSubscribe");
              }

              @Override
              public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
              }

              @Override
              public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
              }

              @Override
              public void onComplete() {
                Log.e(TAG, "onComplete");
              }
            });
  }

  /**
   * 创建Flowable的时候增加了一个参数, 这个参数是用来选择背压,也就是出现上下游流速不均衡的时候应该怎么处理的办法,
   * 这里我们直接用BackpressureStrategy.ERROR这种方式, 这种方式会在出现上下游流速不均衡的时候直接抛出一个异常,
   * 这个异常就是著名的MissingBackpressureException
   */
  private void test16() {
    Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
      @Override
      public void subscribe(FlowableEmitter<Integer> e) throws Exception {
        e.onNext(1);
        Log.e(TAG, "subscribe: emitter=" + 1);
        e.onNext(2);
        Log.e(TAG, "subscribe: emitter=" + 2);
        e.onNext(3);
        Log.e(TAG, "subscribe: emitter=" + 3);
        e.onComplete();
        Log.e(TAG, "subscribe: onComplete");

      }
    }, BackpressureStrategy.ERROR);

    Subscriber<Integer> subscriber = new Subscriber<Integer>() {
      @Override
      public void onSubscribe(Subscription s) {
        Log.e(TAG, "onSubscribe: ");
        s.request(Long.MAX_VALUE);  //注意这句代码，同步情况下，去掉就报错这个异常就是著名的MissingBackpressureException
        //这是因为下游没有调用request, 上游就认为下游没有处理事件的能力, 而这又是一个同步的订阅, 既然下游处理不了,
        // 那上游不可能一直等待吧, 如果是这样, 万一这两根水管工作在主线程里, 界面不就卡死了吗, 因此只能抛个异常来提醒我们.
      }

      @Override
      public void onNext(Integer integer) {
        Log.e(TAG, "onNext: " + integer);
      }

      @Override
      public void onError(Throwable t) {
        Log.e(TAG, "onError: t=" + t);
      }

      @Override
      public void onComplete() {
        Log.e(TAG, "onComplete: ");
      }
    };

    flowable.subscribe(subscriber);
  }

  private void test15() {
    Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        for (int i = 0; ; i++) {
          e.onNext(i);
        }
      }
    }).subscribeOn(Schedulers.io()).sample(2, TimeUnit.SECONDS);
    Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(ObservableEmitter<String> e) throws Exception {
        e.onNext("a");
      }
    }).subscribeOn(Schedulers.io());
    Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
      @Override
      public String apply(Integer integer, String s) throws Exception {
        return integer + s;
      }
    }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        Log.e(TAG, "accept: s=" + s);
      }
    }, new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
        Log.e(TAG, "accept: throwable=" + throwable);
      }
    });
  }

  private void test14() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        for (int i = 0; ; i++) {
          emitter.onNext(i);
        }
      }
    }).subscribeOn(Schedulers.io())
            .sample(2, TimeUnit.SECONDS)  //sample取样
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Exception {
                Log.e(TAG, "" + integer);
              }
            });
  }

  private void test13() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        for (int i = 0; ; i++) {
          emitter.onNext(i);
        }
      }
    }).subscribeOn(Schedulers.io())
            .filter(new Predicate<Integer>() {
              @Override
              public boolean test(Integer integer) throws Exception {
                return integer % 10 == 0;
              }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Exception {
                Log.e(TAG, "" + integer);
              }
            });
  }

  private void test12() {
//    Observable<UserBaseInfoResponse> observable1 =
//            api.getUserBaseInfo(new UserBaseInfoRequest()).subscribeOn(Schedulers.io());
//
//    Observable<UserExtraInfoResponse> observable2 =
//            api.getUserExtraInfo(new UserExtraInfoRequest()).subscribeOn(Schedulers.io());
//
//    Observable.zip(observable1, observable2,
//            new BiFunction<UserBaseInfoResponse, UserExtraInfoResponse, UserInfo>() {
//              @Override
//              public UserInfo apply(UserBaseInfoResponse baseInfo,
//                                    UserExtraInfoResponse extraInfo) throws Exception {
//                return new UserInfo(baseInfo, extraInfo);
//              }
//            }).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Consumer<UserInfo>() {
//              @Override
//              public void accept(UserInfo userInfo) throws Exception {
//                //do something;
//              }
//            });
  }

  //RxJava2不在这个会报错，test11中第二个observable2发送完成后第一个observable会被中断
  static {
    RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
        if (throwable instanceof InterruptedException) {
          Log.e(TAG, "accept:Io interrupted ");
        }
      }
    });
  }

  private void test11() {
    Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        Log.e(TAG, "emit 1");
        emitter.onNext(1);
        Thread.sleep(1000);

        Log.e(TAG, "emit 2");
        emitter.onNext(2);
        Thread.sleep(1000);

        Log.e(TAG, "emit 3");
        emitter.onNext(3);
        Thread.sleep(1000);

        Log.e(TAG, "emit 4");
        emitter.onNext(4);
        Thread.sleep(1000);

        Log.e(TAG, "emit complete1");
        emitter.onComplete();
      }
    }).subscribeOn(Schedulers.io());

    Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(ObservableEmitter<String> emitter) throws Exception {
        Log.e(TAG, "emit A");
        emitter.onNext("A");
        Thread.sleep(1000);

        Log.e(TAG, "emit B");
        emitter.onNext("B");
        Thread.sleep(1000);

        Log.e(TAG, "emit C");
        emitter.onNext("C");
        Thread.sleep(1000);

        Log.e(TAG, "emit complete2");
        emitter.onComplete();
      }
    }).subscribeOn(Schedulers.io());

    Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
      @Override
      public String apply(Integer integer, String s) throws Exception {
        return integer + s;
      }
    }).subscribe(new Observer<String>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.e(TAG, "onSubscribe");
      }

      @Override
      public void onNext(String value) {
        Log.e(TAG, "onNext: " + value);
      }

      @Override
      public void onError(Throwable e) {
        Log.e(TAG, "onError");
      }

      @Override
      public void onComplete() {
        Log.e(TAG, "onComplete");
      }
    });
  }

  private void test10() {
    Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        Log.e(TAG, "emit 1");
        emitter.onNext(1);
        Log.e(TAG, "emit 2");
        emitter.onNext(2);
        Log.e(TAG, "emit 3");
        emitter.onNext(3);
        Log.e(TAG, "emit 4");
        emitter.onNext(4);
        Log.e(TAG, "emit complete1");
        emitter.onComplete();
      }
    });

    Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(ObservableEmitter<String> emitter) throws Exception {
        Log.e(TAG, "emit A");
        emitter.onNext("A");
        Log.e(TAG, "emit B");
        emitter.onNext("B");
        Log.e(TAG, "emit C");
        emitter.onNext("C");
        Log.e(TAG, "emit complete2");
        emitter.onComplete();
      }
    });

    Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
      @Override
      public String apply(Integer integer, String s) throws Exception {
        return integer + s;
      }
    }).subscribe(new Observer<String>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.e(TAG, "onSubscribe: ");
      }

      @Override
      public void onNext(String value) {
        Log.e(TAG, "onNext: " + value);
      }

      @Override
      public void onError(Throwable e) {
        Log.e(TAG, "onError: ");
      }

      @Override
      public void onComplete() {
        Log.e(TAG, "onComplete: ");
      }
    });
  }

  private void test9() {
//    api.register(new RegisterRequest())            //发起注册请求
//            .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
//            .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
//            .doOnNext(new Consumer<RegisterResponse>() {
//              @Override
//              public void accept(RegisterResponse registerResponse) throws Exception {
//                //先根据注册的响应结果去做一些操作
//              }
//            })
//            .observeOn(Schedulers.io())                 //回到IO线程去发起登录请求
//            .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
//              @Override
//              public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
//                return api.login(new LoginRequest());
//              }
//            })
//            .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求登录的结果
//            .subscribe(new Consumer<LoginResponse>() {
//              @Override
//              public void accept(LoginResponse loginResponse) throws Exception {
//                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//              }
//            }, new Consumer<Throwable>() {
//              @Override
//              public void accept(Throwable throwable) throws Exception {
//                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
//              }
//            });
  }

  private void test8() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        e.onNext(1);
        e.onNext(2);
        e.onNext(3);
      }
    }).flatMap(new Function<Integer, ObservableSource<String>>() {
      @Override
      public ObservableSource<String> apply(Integer integer) throws Exception {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
          list.add("I am value " + integer);
        }
        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
      }
    }).subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        Log.e(TAG, "accept: s=" + s);
      }
    });
  }

  private void test7() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        e.onNext(1);
        e.onNext(2);
        e.onNext(3);
      }
    }).map(new Function<Integer, String>() {
      @Override
      public String apply(Integer integer) throws Exception {
        return "This is result " + integer;
      }
    }).subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        Log.e(TAG, "accept: s=" + s);
      }
    });
  }

//  public Observable<List<Record>> test6() {
//    return Observable.create(new ObservableOnSubscribe<List<Record>>() {
//      @Override
//      public void subscribe(ObservableEmitter<List<Record>> emitter) throws Exception {
//        Cursor cursor = null;
//        try {
//          cursor = getReadableDatabase().rawQuery("select * from " + TABLE_NAME, new String[]{});
//          List<Record> result = new ArrayList<>();
//          while (cursor.moveToNext()) {
//            result.add(Db.Record.read(cursor));
//          }
//          emitter.onNext(result);
//          emitter.onComplete();
//        } finally {
//          if (cursor != null) {
//            cursor.close();
//          }
//        }
//      }
//    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//  }

  private void test5() {
    RetrofitUtils retrofitUtils = RetrofitUtils.getInstance(this);
    retrofitUtils.getData(new Observer<EsgResponse>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(EsgResponse value) {
//        Log.e(TAG, "onNext: value=" + value.toString());
        String s = value.toString();
        Logger.e(s);
      }

      @Override
      public void onError(Throwable e) {
        Log.e(TAG, "onError: get esg error");
      }

      @Override
      public void onComplete() {
        Log.e(TAG, "onComplete: get esg complete");
      }
    });
  }

  private void test4(Observable<Integer> observable, Consumer<Integer> consumer) {
    observable.subscribeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Exception {
                Log.e(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
              }
            })
            .observeOn(Schedulers.io())
            .doOnNext(new Consumer<Integer>() {
              @Override
              public void accept(Integer integer) throws Exception {
                Log.e(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
              }
            })
            .subscribe(consumer);
  }

  private Consumer<Integer> createConsumer() {
    Consumer<Integer> consumer = new Consumer<Integer>() {
      @Override
      public void accept(Integer integer) throws Exception {
        Log.e(TAG, "accept: thread=" + Thread.currentThread().getName());
        Log.e(TAG, "accept: integer=" + integer);
      }
    };
    return consumer;
  }

  private Observer<Integer> createObserver() {
    Observer<Integer> observer = new Observer<Integer>() {
      private Disposable disposable;
      private int i;

      @Override
      public void onSubscribe(Disposable d) {
        Log.e(TAG, "onSubscribe: ");
        disposable = d;
      }

      @Override
      public void onNext(Integer value) {
        Log.e(TAG, "onNext: value=" + value);
        i++;
        if (i == 2) {
          Log.e(TAG, "onNext: dispose");
          disposable.dispose();
          Log.e(TAG, "onNext: isDisposed=" + disposable.isDisposed());
        }
      }

      @Override
      public void onError(Throwable e) {
        Log.e(TAG, "onError: ");
      }

      @Override
      public void onComplete() {
        Log.e(TAG, "onComplete: ");
      }
    };
    return observer;
  }

  private Observable<Integer> createObservable() {
    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        Log.e(TAG, "subscribe: thread=" + Thread.currentThread().getName());
        e.onNext(1);
        Log.e(TAG, "subscribe: emitter=" + 1);
        e.onNext(2);
        Log.e(TAG, "subscribe: emitter=" + 2);
        e.onNext(3);
        Log.e(TAG, "subscribe: emitter=" + 3);
        e.onComplete();
        Log.e(TAG, "subscribe: onComplete");
        e.onNext(4);
        Log.e(TAG, "subscribe: emitter=" + 4);
      }
    });
    return observable;
  }

  @OnClick({R.id.start, R.id.request, R.id.btn_movie, tv_get_code})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.start:
        test18();
        break;
      case R.id.request:
        if (mSubscription != null) {
          mSubscription.request(128);
        }
        break;
      case R.id.btn_movie:
//        test20();
//        test21();
        test22();
        break;
      case tv_get_code:
        test28();
        break;
    }
  }
}
