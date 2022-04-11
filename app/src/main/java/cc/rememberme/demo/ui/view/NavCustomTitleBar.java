package cc.rememberme.demo.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import cc.rememberme.demo.R;
import cc.rememberme.demo.databinding.ViewNavCustomTitleBarBinding;

/**
 * @author guojialin
 * @since 2022-01-24 11:20
 */
public class NavCustomTitleBar extends ConstraintLayout {

    private ViewNavCustomTitleBarBinding binding;

    private NavController navController;

    private AppCompatActivity hostActivity;

    public NavCustomTitleBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public NavCustomTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NavCustomTitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public NavCustomTitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ConstraintLayout rootView = (ConstraintLayout) layoutInflater.inflate(R.layout.view_nav_custom_title_bar, this, true);
        binding = ViewNavCustomTitleBarBinding.bind(rootView);

        // default action
        binding.title.setText("Hi 这是标题");
        binding.back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Back Clicked", Toast.LENGTH_SHORT).show();
                if (navController != null) {
                    boolean result = navController.navigateUp();
                    if (!result && hostActivity != null) {
                        hostActivity.finish();
                    } else {
                        setTitle(navController
                                .getCurrentBackStackEntry()
                                .getDestination()
                                .getLabel());
                    }
                }
            }
        });
        binding.action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Action Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTitle(CharSequence title) {
        binding.title.setText(title);
    }

    public void bindNavController(NavController navController, AppCompatActivity activity) {
        this.hostActivity = activity;
        this.navController = navController;
        if (navController != null) {
            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                    setTitle(destination.getLabel());
                }
            });
        }
    }
}
