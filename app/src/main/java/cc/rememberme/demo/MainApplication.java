package cc.rememberme.demo;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.didi.drouter.api.DRouter;
import com.didi.hummer.Hummer;
import com.didi.hummer.HummerConfig;
import com.didi.hummer.adapter.navigator.NavCallback;
import com.didi.hummer.adapter.navigator.NavPage;
import com.didi.hummer.adapter.navigator.impl.DefaultNavigatorAdapter;
import com.didichuxing.doraemonkit.DoKit;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.mmkv.MMKV;

import butterknife.ButterKnife;
import cc.rememberme.demo.config.GlobalConfig;

/**
 * @author : guojialin
 * @date : 2021/8/8 09:24
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // logger
        initLogger();

        // butterknife
        ButterKnife.setDebug(BuildConfig.DEBUG);

        // DoKit
        new DoKit.Builder(this)
                .productId("12345678")
                .build();

        // mmkv
        String rootDir = MMKV.initialize(this);
        System.out.println("mmkv root: " + rootDir);

        // hummer - https://hummer.didi.cn/doc#/zh-CN/android_doc
        initHummer();

        // init drouter
        initDRouter();

    }

    private void initDRouter() {
        DRouter.init(this);
    }

    private void initHummer() {
                /*
        HummerConfig config = new HummerConfig.Builder()
                // 自定义namespace（用于业务线隔离，需和Hummer容器中的namespace配合使用，可选）
                .setNamespace("hummer-demo-namespace")
                // JS日志回调（可选）
                .setJSLogger((level, msg) -> {
                    Logger.d(msg);
                })
                // JS异常回调（可选）
                .setExceptionCallback(e -> {
                })
                // RTL支持（可选）
                .setSupportRTL(false)
                // 字体文件Assets目录设置（可选）
                .setFontsAssetsPath("fonts")
                // 自定义路由（可在这里指定自定义Hummer容器，可选）
                .setNavigatorAdapter(new DefaultNavigatorAdapter())
                // 自定义图片库（可选）
                .setImageLoaderAdapter(new DefaultImageLoaderAdapter())
                // 自定义网络库（可选）
                .setHttpAdapter(new DefaultHttpAdapter())
                // 自定义定位（可选）
                .setLocationAdapter(new DefaultLocationAdapter())
                // 自定义持久化存储（可选）
                .setStorageAdapter(new DefaultStorageAdapter())
                // 构造HummerConfig
                .builder();
                 */

        HummerConfig config2 = new HummerConfig.Builder()
                .setNamespace("relearn-android")
                .setJSLogger((level, msg) -> Log.d("HummerJS_2", msg))
                .setEventTracer((event, params) -> Log.i("zdf", "[EventTracer_2] event: " + event + ", params: " + params))
                .setExceptionCallback(e -> {
                    Log.e("zdf", "HummerException_2", e);
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                })
                .setNavigatorAdapter(new DefaultNavigatorAdapter() {
                    @Override
                    public void openPage(Context context, NavPage page, NavCallback callback) {
                        Log.v("zdf", "relearn-android, openPage");
                    }
                })
                .builder();

        Hummer.init(this, config2);
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("RMM")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @org.jetbrains.annotations.Nullable String tag) {
                return GlobalConfig.LOG_DEBUG;
            }
        });
    }
}
