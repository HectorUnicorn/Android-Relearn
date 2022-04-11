package cc.rememberme.demo;

import android.app.Application;

import com.didi.drouter.api.DRouter;
import com.orhanobut.logger.Logger;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author guojialin
 * @since 2022-02-08 17:23
 */
public class ApplicationObserver implements LifecycleObserver {

    private Application app;

    public ApplicationObserver(Application application) {
        this.app = application;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onAppCreate() {
        // 只会调用一次
        Logger.d("Application Created!");
        Logger.d("init DRouter!");
        initDRouter();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppStart() {
        Logger.d("Application Started!");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAppResume() {
        Logger.d("Application Resumed!");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onAppPause() {
        Logger.d("Application Paused!");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppStop() {
        Logger.d("Application Stopped!");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onAppDestroy() {
        // 永远不调用
        Logger.d("Application Destroyed!");
    }

    private void initDRouter() {
        DRouter.init(this.app);
    }
}
