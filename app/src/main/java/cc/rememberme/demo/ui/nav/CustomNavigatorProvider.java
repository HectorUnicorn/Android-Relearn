package cc.rememberme.demo.ui.nav;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.navigation.ActivityNavigator;

/**
 * @author guojialin
 * @since 2022-01-27 12:05
 */
public class CustomNavigatorProvider extends ActivityNavigator {

    public CustomNavigatorProvider(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public Destination createDestination() {
        return super.createDestination();
    }

}
