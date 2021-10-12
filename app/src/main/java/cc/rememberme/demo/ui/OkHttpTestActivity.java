package cc.rememberme.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import cc.rememberme.demo.base.activity.BaseActivity;
import cc.rememberme.demo.databinding.ActivityOkHttpTestBinding;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpTestActivity extends BaseActivity {


    /**
     * Moshi是Square公司在2015年6月开源的有关Json的反序列化及序列化的框架,天然对kotlin友好
     * https://github.com/square/moshi
     * https://juejin.cn/post/6844903704278073357
     */
    private static final Moshi MOSHI = new Moshi.Builder().build();
    public static final String ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors";

    private ActivityOkHttpTestBinding binding;
    private Button btnGetData;
    private TextView tvConsole;

    private OkHttpClient okHttpClient;

    private static final JsonAdapter<List<Contributor>> CONTRIBUTORS_JSON_ADAPTER = MOSHI.adapter(
            Types.newParameterizedType(List.class, Contributor.class));

    public static void launch(Activity context, String title) {
        Intent intent = new Intent(context, OkHttpTestActivity.class);
        intent.putExtra(KEY_TITLE, title);
        context.startActivity(intent);
    }

    static class Contributor {
        String login;
        int contributions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOkHttpTestBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // client
        okHttpClient = new OkHttpClient();

        // init views
        btnGetData = binding.btnRetriveData;
        tvConsole = binding.tvConsole;

        // add click listeners
        addClickListeners();
    }

    private void addClickListeners() {
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    private void sendRequest() {
        this.tvConsole.setText("Loading...");
        Executor executor = new ScheduledThreadPoolExecutor(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(ENDPOINT)
                        .build();

                try (Response response = okHttpClient.newCall(request).execute()) {
                    // Deserialize HTTP response to concrete type.
                    ResponseBody body = response.body();
                    List<Contributor> contributors = CONTRIBUTORS_JSON_ADAPTER.fromJson(body.source());

                    // Sort list by the most contributions.
                    Collections.sort(contributors, (c1, c2) -> c2.contributions - c1.contributions);

                    // Output list of contributors.
                    StringBuffer sb = new StringBuffer();
                    sb.append("project contributors(commits):\n\n");
                    for (Contributor contributor : contributors) {
                        sb.append(contributor.login + ": " + contributor.contributions + "\n\n");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvConsole.setText(sb.toString());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvConsole.setText("网络错误！");
                        }
                    });
                }
            }
        });

    }
}