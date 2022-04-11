package cc.rememberme.demo.ui.nav.fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import cc.rememberme.demo.R;
import cc.rememberme.demo.databinding.FragmentNavHomeBinding;
import cc.rememberme.demo.ui.nav.NavBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavHomeFragment extends NavBaseFragment {

    private FragmentNavHomeBinding homeNavBinding;
    private static int notificationId = 1;

    public NavHomeFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NavHomeFragment.
     */
    public static NavHomeFragment newInstance(String param1, String param2) {
        NavHomeFragment fragment = new NavHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onHandleArguments(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeNavBinding = FragmentNavHomeBinding.inflate(inflater);
        homeNavBinding.btnNav2Second.setOnClickListener(v -> {
            Bundle args = new NavHomeFragmentArgs.Builder()
                    .setUserName("Jack").setAge(18).build().toBundle();
            Navigation.findNavController(v).navigate(R.id.action_navHomeFragment_to_navSecondFragment, args);
        });

        homeNavBinding.btnSendNotification.setOnClickListener(v -> {
            sendNotification(v);
            Navigation.findNavController(v).navigate(R.id.action_navHomeFragment_to_navLifecycleFragment);
        });

        return homeNavBinding.getRoot();
    }

    private void sendNotification(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(getActivity().getPackageName(),
                    "NavChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My Nav Notification Channel");
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat
                .Builder(getActivity(), getActivity().getPackageName())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Deep Link")
                .setContentText("点击试试..")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent(v))
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(notificationId++, notification);
    }

    private PendingIntent getPendingIntent(View v) {
        Bundle args = new NavHomeFragmentArgs.Builder().setUserName("Jack").setAge(18).build().toBundle();
        return Navigation.findNavController(v).createDeepLink()
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.navThirdFragment)
                .setArguments(args)
                .createPendingIntent();
    }

}