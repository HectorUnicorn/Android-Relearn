package cc.rememberme.demo.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cc.rememberme.demo.base.fragment.BaseFragment;
import cc.rememberme.demo.databinding.FragmentMainFeatureListBinding;
import cc.rememberme.demo.ui.hummer.HummerSinglePageActivity;
import cc.rememberme.demo.ui.main.PageViewModel;
import cc.rememberme.demo.ui.main.adapter.FeatureListAdapter;
import cc.rememberme.demo.ui.rxjava.RxJavaActivity;

public class FeatureListFragment extends BaseFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private boolean isInited = false;

    private PageViewModel pageViewModel;
    private FragmentMainFeatureListBinding binding;
    private RecyclerView recyclerView;
    private FeatureListAdapter featureListAdapter;
    private LinearLayoutManager mLayoutManager;

    public static FeatureListFragment newInstance(int index) {
        FeatureListFragment fragment = new FeatureListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMainFeatureListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.featureRecyclerView;

        /**
         * 1 ?????????RecyclerView
         */
        //??????????????????
        recyclerView.setHasFixedSize(true);
        //??????????????????
        mLayoutManager = new LinearLayoutManager(this.getContext());
        //????????????
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //???RecyclerView?????????????????????
        recyclerView.setLayoutManager(mLayoutManager);
        //??????????????????????????????
        featureListAdapter = new FeatureListAdapter(this.getContext());
        recyclerView.setAdapter(featureListAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isInited) {
//            HummerSinglePageActivity.launch((Activity) this.getContext());
            isInited = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
