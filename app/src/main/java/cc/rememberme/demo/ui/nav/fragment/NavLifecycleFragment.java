package cc.rememberme.demo.ui.nav.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.security.Permission;
import java.util.Map;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.rememberme.demo.R;
import cc.rememberme.demo.databinding.FragmentNavLifecycleBinding;
import cc.rememberme.demo.service.MyLocationService;
import cc.rememberme.demo.ui.nav.NavBaseFragment;
import cc.rememberme.demo.utils.RmPermissionUtils;

/**
 * @author guojialin
 * @since 2022-02-02 19:53
 */
public class NavLifecycleFragment extends NavBaseFragment {

    private FragmentNavLifecycleBinding binding;
    private ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions;
    private ActivityResultLauncher<String[]> activityResultLauncher;
    private boolean isPermited = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPermited = RmPermissionUtils.hasLocationPermission(getContext());
        Logger.d("定位权限：" + isPermited);
        requestMultiplePermissions = new ActivityResultContracts.RequestMultiplePermissions();
        activityResultLauncher = registerForActivityResult(requestMultiplePermissions, new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> permissions) {
                boolean result = false;
                for (String key : permissions.keySet()) {
                    Logger.d("permission:" + key + " - " + permissions.get(key));
                    if (permissions.get(key)) {
                        result = true;
                    } else {
                        result = false;
                        break;
                    }
                }
                isPermited = result;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // launcher调起权限授权
        if (!isPermited) {
            activityResultLauncher.launch(RmPermissionUtils.LOCATION_PERMISSIONS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNavLifecycleBinding.inflate(inflater);
        getLifecycle().addObserver(binding.lifecycleChronometer);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btnStartService)
    public void start() {
        Toast.makeText(getContext(), "开始记录位置", Toast.LENGTH_SHORT).show();
        getContext().startService(new Intent(getActivity(), MyLocationService.class));
    }

    @OnClick(R.id.btnStopService)
    public void stop() {
        Toast.makeText(getContext(), "结束记录位置", Toast.LENGTH_SHORT).show();
        getContext().stopService(new Intent(getActivity(), MyLocationService.class));
    }

    @Override
    public void onHandleArguments(Bundle arguments) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
