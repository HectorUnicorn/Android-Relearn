package cc.rememberme.demo.ui.view.lifecycle;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Chronometer;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 自定义的计时View
 *
 * @author guojialin
 * @since 2022-02-02 21:38
 */
public class LifeCycleChronometer extends Chronometer implements LifecycleObserver {

    private long elapsedTime;

    public LifeCycleChronometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startMeter() {
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void stopMeter() {
        elapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }

}
