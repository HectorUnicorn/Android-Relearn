package cc.rememberme.demo.ui.nav.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import androidx.navigation.Navigation;
import cc.rememberme.demo.R;
import cc.rememberme.demo.ui.nav.NavBaseFragment;

public class NavThirdFragment extends NavBaseFragment {

    @Override
    public void onHandleArguments(Bundle arguments) {
        if (arguments != null) {
            NavHomeFragmentArgs arg = NavHomeFragmentArgs.fromBundle(arguments);
            Logger.d("args:" + arg.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nav_third, container, false);
        rootView.findViewById(R.id.btnNav2Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(R.id.action_navThirdFragment_to_navHomeFragment);
            }
        });
        return rootView;
    }
}