package cc.rememberme.demo.ui.nav.fragment;

import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import androidx.navigation.Navigation;
import cc.rememberme.demo.R;
import cc.rememberme.demo.databinding.FragmentNavSecondBinding;
import cc.rememberme.demo.ui.nav.NavBaseFragment;

public class NavSecondFragment extends NavBaseFragment {

    private String userName;
    private int age;

    FragmentNavSecondBinding secondBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onHandleArguments(Bundle arguments) {
        NavHomeFragmentArgs args = NavHomeFragmentArgs.fromBundle(arguments);
        Logger.d(args.getUserName() + " is " + args.getAge() + " years old.");
        this.userName = args.getUserName();
        this.age = args.getAge();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        secondBinding = FragmentNavSecondBinding.inflate(inflater);
        secondBinding.btnNav2Third.setOnClickListener(v -> Navigation.findNavController(v)
                .navigate(R.id.action_navSecondFragment_to_navThirdFragment));

        // deepLink mode - 目前看back stack正常
        secondBinding.btnDeekLink.setOnClickListener(v -> {
            Uri deepLink = Uri.parse(String.format("relearn://demo.relearn.cc/nav-third?userName=%s&age=%s", userName, age));
            Navigation.findNavController(v).navigate(deepLink);
        });

        return secondBinding.getRoot();
    }
}