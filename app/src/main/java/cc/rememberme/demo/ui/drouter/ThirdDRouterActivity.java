package cc.rememberme.demo.ui.drouter;

import android.os.Bundle;

import com.didi.drouter.annotation.Router;
import com.orhanobut.logger.Logger;

import androidx.annotation.Nullable;
import cc.rememberme.demo.base.activity.BaseActivity;

/**
 * @author guojialin
 * @since 2022-06-21 20:33
 */
@Router(scheme = "didi", host = "router", path = "/logout")
public class ThirdDRouterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Logger.d("extras:" + extras.toString());
    }

}