package cc.rememberme.demo.ui.drouter;

import com.didi.drouter.annotation.Router;
import com.didi.drouter.router.IRouterHandler;
import com.didi.drouter.router.Request;
import com.didi.drouter.router.Result;

import androidx.annotation.NonNull;

/**
 * @author guojialin
 * @since 2022-06-21 21:04
 */
@Router(scheme = "didi", host = "router", path = "/p")
public class SendOrderHandler implements IRouterHandler {

    @Override
    public void handle(@NonNull Request request, @NonNull Result result) {

    }

}
