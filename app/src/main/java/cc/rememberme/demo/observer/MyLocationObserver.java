package cc.rememberme.demo.observer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.orhanobut.logger.Logger;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 监听位置变化
 *
 * @author guojialin
 * @since 2022-02-03 09:48
 */
public class MyLocationObserver implements LifecycleObserver {

    private Context context;
    private LocationManager locationManager;
    private MyLocationListener myLocationListener;

    public MyLocationObserver(Context context) {
        this.context = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void start() {
        Logger.d("== start get Location");
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        myLocationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 1,
                myLocationListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void stop() {
        Logger.d("== stop get Location");
        locationManager.removeUpdates(myLocationListener);
    }

    static class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Logger.d("location Changed:" + location.toString());
        }
    }

}
