package cc.rememberme.demo.ui.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import cc.rememberme.demo.base.activity.BaseActivity;
import cc.rememberme.demo.databinding.ActivityRxjavaBinding;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author guojialin
 * @since 2021-09-29 14:25
 */
public class RxJavaActivity extends BaseActivity implements View.OnClickListener {

    private ActivityRxjavaBinding viewBinding;
    private Button btnAsync;
    private TextView tvRxJavaConsole;

    private Observer<Integer> mCommonObserver = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
            Logger.d("开始采用subscribe连接");
        }

        @Override
        public void onNext(Integer value) {
            Logger.d("接收到的整数是:" + value);
        }

        @Override
        public void onError(Throwable e) {
            Logger.d("对Error事件作出响应");
        }

        @Override
        public void onComplete() {
            Logger.d("对Complete事件作出响应");
        }
    };

    private Observer<String> mStringObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Logger.d("开始采用subscribe连接");
        }

        @Override
        public void onNext(String value) {
            Logger.d("接收到的str是:" + value);
        }

        @Override
        public void onError(Throwable e) {
            Logger.d("对Error事件作出响应");
        }

        @Override
        public void onComplete() {
            Logger.d("对Complete事件作出响应");
        }
    };

    public static void launch(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityRxjavaBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        btnAsync = viewBinding.triggerTask;
        tvRxJavaConsole = viewBinding.tvRxJavaConsole;

        // basic
        Flowable.just("Hello world").subscribe(Logger::d);

        btnAsync.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        basicSubscription();
//        basicRxAndroidSubscription();
//        basicSubscribeDisposable();
//        flowAble();
//        flowRuntime();
//        mapTest();
//        flatMapTest();
//        concatMapTest();
//        bufferTest();
//        collectionTest();
//        timerTest();
        mergeTest();
    }

    private void mergeTest() {
        /*
         * 设置第1个Observable：通过网络获取数据
         * 此处仅作网络请求的模拟
         **/
        Observable<String> network = Observable.just("网络");

        /*
         * 设置第2个Observable：通过本地文件获取数据
         * 此处仅作本地文件请求的模拟
         **/
        Observable<String> file = Observable.just("本地文件");

        /*
         * 通过merge（）合并事件 & 同时发送事件
         **/
        Observable.merge(network, file)
                .subscribe(new Observer<String>() {
                    // 用于存放最终展示的数据
                    String result = "数据源来自 = ";

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Logger.d("数据源有： " + value);
                        result += value + "+";
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("对Error事件作出响应");
                    }

                    // 接收合并事件后，统一展示
                    @Override
                    public void onComplete() {
                        Logger.d("获取数据完成");
                        Logger.d(result);
                    }
                });
    }

    private void timerTest() {
//        Observable<Integer> obvs1 = Observable.timer(2, TimeUnit.SECONDS).just(1, 2, 3, 4);
//        obvs1.subscribe(mCommonObserver);
//
//        Observable<Integer> obvs2 = Observable.interval(3, 1, TimeUnit.SECONDS).just(1, 2, 3, 4);
//        obvs2.subscribe(mCommonObserver);

        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        // 快速创建1个被观察者对象（Observable）
        // 发送事件的特点：每隔指定时间 就发送 事件，可指定发送的数据的数量
        Observable
                .intervalRange(3, 10, 2, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.d("开始采用subscribe连接");
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                Logger.d("接收到的整数是:" + aLong);
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Logger.d("对Complete事件作出响应");
            }
        });

    }

    /**
     * 对于数组的操作
     */
    private void collectionTest() {
        Integer[] items = {0, 1, 2, 3, 4}; // 声明并分配空间然后.赋值 等同于 int[] arr= new []{0, 1, 2, 3, 4};
        Observable.fromArray(items);


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list);

        // 该方法创建的被观察者对象发送事件的特点：仅发送Complete事件，直接通知完成
        // 即观察者接收后会直接调用onCompleted（）
        Observable observable1 = Observable.empty();

        // 该方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
        // 可自定义异常
        // 即观察者接收后会直接调用onError（）
        Observable observable2 = Observable.error(new RuntimeException());

        // 该方法创建的被观察者对象发送事件的特点：不发送任何事件
        // 即观察者接收后什么都不调用
        Observable observable3 = Observable.never();

        // 1. 第1次对i赋值
        Integer num = 10;

        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建
        final Integer fNum = num;
        Observable<Integer> observable = Observable.defer(new Supplier<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> get() throws Throwable {
                return Observable.just(fNum);
            }
        });

        // 2. 第2次对i赋值
        num = 15;

        // 3. 观察者开始订阅
        // 注：此时，才会调用defer（）创建被观察者对象（Observable）
        observable.subscribe(mCommonObserver);
    }

    /**
     * 滑动窗口，buffer是3个，每次向后滑动一格
     */
    private void bufferTest() {
        // 被观察者 需要发送5个数字
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 1)
                // 设置缓存区大小 & 步长
                // 缓存区大小 = 每次从被观察者中获取的事件数量
                // 步长 = 每次获取新事件的数量
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Integer> stringList) {
                        Logger.d(" 缓存区里的事件数量 = " + stringList.size());
                        for (Integer value : stringList) {
                            Logger.d(" 事件 = " + value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("对Complete事件作出响应");
                    }
                });
    }

    /**
     * 作用：类似FlatMap（）操作符
     * 与FlatMap（）的 区别在于：拆分 & 重新合并生成的事件序列 的顺序 = 被观察者旧序列生产的顺序
     */
    private void concatMapTest() {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用concatMap（）变换操作符
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过concatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).delay(8, TimeUnit.SECONDS, false) // 延迟执行会被放在一个线程中
                .subscribe(new Consumer<String>() {
                    StringBuffer sb = new StringBuffer();

                    @Override
                    public void accept(String s) throws Exception {
                        sb.append(s + "\n");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvRxJavaConsole.setText(sb.toString());
                            }
                        });
                    }
                });
    }

    /**
     * 作用：将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送
     */
    private void flatMapTest() {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
            // 采用flatMap（）变换操作符
        }).delay(5, TimeUnit.SECONDS, false) // 延迟执行会被放在一个线程中
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("我是事件 " + integer + "拆分后的子事件" + i);
                            // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                            // 最终合并，再发送给被观察者
                        }
                        // 返回值是个List，但是所有List最后排成同一个列表
                        return Observable.fromIterable(list);
                    }
                    // subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError)
                }).subscribe(new Consumer<String>() {

                                 StringBuffer sb = new StringBuffer();

                                 @Override
                                 public void accept(String s) throws Exception {
                                     sb.append(s + "\n");
                                     runOnUiThread(new Runnable() {
                                         @Override
                                         public void run() {
                                             tvRxJavaConsole.setText(sb.toString());
                                         }
                                     });

                                 }
                             }, new Consumer<Throwable>() {
                                 @Override
                                 public void accept(Throwable throwable) throws Exception {
                                     throwable.printStackTrace();
                                     runOnUiThread(new Runnable() {
                                         @Override
                                         public void run() {
                                             tvRxJavaConsole.setText(throwable.getStackTrace().toString());
                                         }
                                     });
                                 }
                             }
        );
    }

    private void mapTest() {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {

            // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

            }
            // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "使用 Map变换操作符 将事件" + integer + "的参数从 整型" + integer + " 变换成 字符串类型" + integer;
            }
        }).subscribe(new Consumer<String>() {

            // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
            StringBuffer sb = new StringBuffer();

            @Override
            public void accept(String s) throws Exception {
                Logger.d(s);
                sb.append(s + "\n");
                tvRxJavaConsole.setText(sb.toString());
            }
        });
    }

    private void basicSubscribeDisposable() {

        Observer<Integer> observer = new Observer<Integer>() {
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Logger.d("==onNext:" + integer);
                if (integer % 5 == 0) {
                    Logger.d("==切断连接：isDisposed!");
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        Observable.range(1, 10).subscribe(observer);
    }

    // 可中断的订阅链
    private void flowRuntime() {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 5 == 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        })
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    // range、filter、map
    private void flowAble() {
        Flowable<Integer> flow = Flowable.range(1, 20)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);
        flow.subscribe(Logger::d);
    }

    // 基本订阅逻辑
    private void basicSubscription() {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onNext(5);
            emitter.onComplete();
        });

        observable.subscribe(mCommonObserver);
    }

    // Android 基本订阅
    private void basicRxAndroidSubscription() {
        Observable.just("one", "two", "three", "four", "five")
                .delay(2, TimeUnit.SECONDS, false) // 一次性延迟一个时间，然后立即执行完
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        tvRxJavaConsole.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
