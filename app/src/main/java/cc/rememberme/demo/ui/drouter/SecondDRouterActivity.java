package cc.rememberme.demo.ui.drouter;

import android.os.Bundle;

import com.didi.drouter.annotation.Router;
import com.orhanobut.logger.Logger;

import androidx.annotation.Nullable;
import cc.rememberme.demo.base.activity.BaseActivity;

/**
 * @author guojialin
 * @since 2021-10-11 11:22
 */
@Router(scheme = "didi", host = "router", path = "/login")
public class SecondDRouterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Logger.d("extras:" + extras.toString());
    }
}
