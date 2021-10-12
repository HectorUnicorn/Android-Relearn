package cc.rememberme.demo.ui.drouter;

import android.os.Bundle;
import android.view.View;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrouterMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
