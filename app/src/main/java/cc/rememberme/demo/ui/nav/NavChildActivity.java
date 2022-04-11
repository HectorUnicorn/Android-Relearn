package cc.rememberme.demo.ui.nav;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import cc.rememberme.demo.R;
import cc.rememberme.demo.databinding.ActivityNavChildBinding;
import cc.rememberme.demo.databinding.ActivityNavMainBinding;

/**
 * 第二级Route Host
 *
 * @author guojialin
 * @since 2022-01-25 15:46
 */
public class NavChildActivity extends AppCompatActivity {

    private ActivityNavChildBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavChildBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation
                .findNavController(this, R.id.childFragmentContainer);

        // ActionBar
//        NavigationUI.setupActionBarWithNavController(this, navController);

        // 自定义TitleBar，处理返回事件
        binding.navCustomTitleBar.bindNavController(navController, this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // actionBar的返回按钮处理
        // 通过nav_graph的配置做了解耦
        NavController navController = Navigation
                .findNavController(this, R.id.childFragmentContainer);
        return navController.navigateUp();
    }

}
