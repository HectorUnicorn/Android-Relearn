package cc.rememberme.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import cc.rememberme.demo.R;

public class OkHttpTestActivity extends AppCompatActivity {

    private static final String KEY_PARAM = "KEY";

    public static void launch(Activity context, String param) {
        Intent intent = new Intent(context, OkHttpTestActivity.class);
        intent.putExtra(KEY_PARAM, param);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);



    }
}