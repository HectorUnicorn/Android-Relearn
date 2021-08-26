package cc.rememberme.demo;

import android.app.Application;
import android.support.annotation.Nullable;

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

        String rootDir = MMKV.initialize(this);
        System.out.println("mmkv root: " + rootDir);

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
            public boolean isLoggable(int priority, @Nullable @org.jetbrains.annotations.Nullable String tag) {
                return GlobalConfig.LOG_DEBUG;
            }
        });
    }
}
