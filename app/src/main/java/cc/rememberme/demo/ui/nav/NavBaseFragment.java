package cc.rememberme.demo.ui.nav;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * Fragment的积累
 *
 * @author guojialin
 * @since 2022-01-25 11:51
 */
public abstract class NavBaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && args.containsKey(NavController.KEY_DEEP_LINK_INTENT)) {
            Intent intent = (Intent) getArguments().getParcelable(NavController.KEY_DEEP_LINK_INTENT);
            Bundle data = intent.getExtras();
            if (data == null) {
                data = new Bundle();
            }
            Uri uri = intent.getData();
            if (uri != null) {
                for (String key : uri.getQueryParameterNames()) {
                    String value = uri.getQueryParameter(key);
                    if (value != null) {
                        // 自动处理数字类型，浮点一律处理为double，否则为int
                        if (TextUtils.isDigitsOnly(value)) {
                            if (value.contains(".")) {
                                data.putDouble(key, Double.parseDouble(value));
                            } else {
                                data.putInt(key, Integer.parseInt(value));
                            }
                        } else {
                            data.putString(key, value);
                        }
                    }
                }
            }
            onHandleArguments(data);
        } else {
            onHandleArguments(args);
        }
        Toast.makeText(getContext(), getActivity().getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    public abstract void onHandleArguments(Bundle arguments);

}
