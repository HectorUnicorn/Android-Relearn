package cc.rememberme.demo.service;

import com.orhanobut.logger.Logger;

import androidx.lifecycle.LifecycleService;
import cc.rememberme.demo.observer.MyLocationObserver;

/**
 * @author guojialin
 * @since 2022-02-03 09:47
 */
public class MyLocationService extends LifecycleService {

    public MyLocationService() {
        Logger.d("MyLocationService - constructor");
        MyLocationObserver observer = new MyLocationObserver(this);
        getLifecycle().addObserver(observer);
    }

}
