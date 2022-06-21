package cc.rememberme.demo.ui.drouter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.didi.drouter.api.DRouter;
import com.didi.drouter.api.Extend;
import com.didi.drouter.router.Result;
import com.didi.drouter.router.RouterCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cc.rememberme.demo.base.activity.BaseActivity;
import cc.rememberme.demo.databinding.ActivityDrouterMainBinding;

/**
 * For DRouter Test
 *
 * @author guojialin
 * @since 2021-10-10 11:56
 */
public class DRouterMainActivity extends BaseActivity {

    private ActivityDrouterMainBinding binding;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, DRouterMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrouterMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnJump.setOnClickListener(v -> {
            DRouter.build("didi://router/login")
                    .putExtra("key", "value")
                    .start(this, new RouterCallback() {
                                @Override
                                public void onResult(@NonNull Result result) {
                                    boolean r = result.isActivityStarted();
                                }
                            }
                    );
        });

        binding.btnJump2.setOnClickListener(v -> {
            Intent intent = new Intent(this, ThirdDRouterActivity.class);
            DRouter.build("didi://router/logout")
                    .putExtra("key1", "value1")
                    .start(this, new RouterCallback.ActivityCallback() {
                        @Override
                        public void onActivityResult(int resultCode, Intent data) {

                        }
                    });
        });
    }
}
