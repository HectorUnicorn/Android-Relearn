package cc.rememberme.demo.ui.main.fragment;

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
import cc.rememberme.demo.ui.main.PageViewModel;
import cc.rememberme.demo.ui.main.adapter.FeatureListAdapter;

public class FeatureListFragment extends BaseFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

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
         * 1 初始化RecyclerView
         */
        //设置固定大小
        recyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this.getContext());
        //垂直方向
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //创建适配器，并且设置
        featureListAdapter = new FeatureListAdapter(this.getContext());
        recyclerView.setAdapter(featureListAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
