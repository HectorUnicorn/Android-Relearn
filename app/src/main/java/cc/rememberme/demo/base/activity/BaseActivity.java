package cc.rememberme.demo.base.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity基类
 *
 * @author : guojialin
 * @date : 2021-08-28 16:41
 */
public class BaseActivity extends AppCompatActivity {

    protected ActionBar actionBar;
    protected static final String KEY_TITLE = "TITLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        String title = getIntent().getExtras() != null ? getIntent().getStringExtra(KEY_TITLE) : "";
        actionBar.setTitle(TextUtils.isEmpty(title) ? this.getClass().getSimpleName() : title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
