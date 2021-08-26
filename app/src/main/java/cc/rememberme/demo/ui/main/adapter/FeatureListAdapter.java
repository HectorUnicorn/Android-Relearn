package cc.rememberme.demo.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cc.rememberme.demo.R;
import cc.rememberme.demo.constant.FeatureItemEnum;
import cc.rememberme.demo.model.FeatureItem;
import cc.rememberme.demo.ui.OkHttpTestActivity;
import cc.rememberme.demo.ui.butterknife.ButterKnifeActivity;
import cc.rememberme.demo.ui.layout.LayoutDemoActivity;
import cc.rememberme.demo.ui.main.listener.OnRVItemClickListener;

/**
 * @author : guojialin
 * @date : 2021/8/7 13:14
 */
public class FeatureListAdapter extends RecyclerView.Adapter<FeatureListAdapter.ViewHolder> implements OnRVItemClickListener {


    private List<FeatureItem> dataList = new ArrayList<>();

    private Context mContext;

    private LayoutInflater layoutInflater;

    public FeatureListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        initFeatures();
    }

    private void initFeatures() {
        for (FeatureItemEnum enumItem : FeatureItemEnum.values()) {
            dataList.add(new FeatureItem(enumItem, null));
        }
    }

    @NonNull
    @NotNull
    @Override
    public FeatureListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_main_feature_view, parent, false);
        ViewHolder holder = null;
        if (view.getTag() != null && view.getTag() instanceof ViewHolder) {
            holder = (ViewHolder) view.getTag();
        } else {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("OnClick - position: " + view.getTag());
                OnItemClicked(v, (Integer) view.getTag());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeatureListAdapter.ViewHolder holder, int position) {
        FeatureItem item = dataList.get(position);
        holder.textItemView.setText(item.getId() + "  " + item.getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void OnItemClicked(View view, int position) {
        FeatureItem item = dataList.get(position);
        Logger.d(item);
        switch (item.getItemEnum()) {
            case LAYOUT:
                Intent intent = new Intent(view.getContext(), LayoutDemoActivity.class);
                mContext.startActivity(intent);
                break;
            case DATA_BINDING:

                break;
            case TASK_AFFINITY:

                break;
            case VIEW_COMPONENTS:

                break;
            case SERVICE:

                break;
            case BROADCAST_RECEIVER:

                break;
            case CONTENT_PROVIDER:

                break;
            case INTENT:

                break;
            case APPLICATION:

                break;
            case RXJAVA:

                break;
            case CUSTOMIZED_VIEW:

                break;
            case TOUCH_EVENT:

                break;
            case DRAW:

                break;
            case ANIM:

                break;
            case LOGGER:

                break;
            case SCREEN:

                break;
            case MULTI_LANGUAGE:

                break;
            case API_LEVELS:

                break;
            case HANDLER:

                break;
            case SYSTEM_API:

                break;
            case ASYNC_TASKS:

                break;
            case NETWORK:

                break;
            case RICH_MEDIA:

                break;
            case FILE_DOWNLOADER:

                break;
            case BUILD:

                break;
            case ANDROID_ANNOTATION:

                break;
            case DESIGN_PATTERN:

                break;
            case PERMISSION:

                break;
            case IPC:

                break;
            case JNI_NKD:

                break;
            case HOTFIX:

                break;
            case AOP:

                break;
            case BUTTER_KNIFE:
                ButterKnifeActivity.startActivity((Activity) mContext);
                break;
            case OKHTTP:
                OkHttpTestActivity.launch((Activity) mContext, "OK!");
                break;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textItemView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textItemView = itemView.findViewById(R.id.tv_item);
        }
    }
}
