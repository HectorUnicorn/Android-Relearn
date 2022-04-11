package cc.rememberme.demo.ui.nav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import cc.rememberme.demo.R;
import cc.rememberme.demo.databinding.ActivityNavMainBinding;

/**
 * 导航管理首页
 *
 * @author guojialin
 * @since 2022-01-21 15:14
 */
public class NavMainActivity extends AppCompatActivity {

    public static void launch(Activity act) {
        Intent intent = new Intent(act, NavMainActivity.class);
        act.startActivity(intent);
    }

    private ActivityNavMainBinding binding;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        navController = Navigation
                .findNavController(this, R.id.fragmentContainerView);
//        navController.getNavigatorProvider().addNavigator(new CustomNavigatorProvider(this));

        // ActionBar
//        NavigationUI.setupActionBarWithNavController(this, navController);

        // 自定义TitleBar，处理返回事件
        binding.navCustomTitleBar.bindNavController(navController, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        navController.handleDeepLink(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // actionBar的返回按钮处理
        // 通过nav_graph的配置做了解耦
        NavController navController = Navigation
                .findNavController(this, R.id.fragmentContainerView);
        return navController.navigateUp();
    }
}
