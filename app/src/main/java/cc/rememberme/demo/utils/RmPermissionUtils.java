package cc.rememberme.demo.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

/**
 * 权限工具类
 *
 * @author guojialin
 * @since 2022-02-07 11:39
 */
public class RmPermissionUtils {

    public static String[] LOCATION_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private static boolean lacksPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_DENIED;
    }

    public static boolean hasLocationPermission(Context context) {
        return hasPermissions(context, LOCATION_PERMISSIONS);
    }

    private static boolean hasPermissions(Context context, String[] permissions) {
        if (context == null || permissions == null || permissions.length == 0) {
            return false;
        }
        for (String p : permissions) {
            if (lacksPermission(context, p)) {
                return false;
            }
        }
        return true;
    }


}
