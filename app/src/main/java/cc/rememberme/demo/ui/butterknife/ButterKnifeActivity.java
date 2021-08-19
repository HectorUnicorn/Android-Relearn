package cc.rememberme.demo.ui.butterknife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.rememberme.demo.R;

@Deprecated
public class ButterKnifeActivity extends AppCompatActivity {

    public static void startActivity(Activity context) {
        Intent intent = new Intent(context, ButterKnifeActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.imgLogo)
    ImageView mLogoView;

    @BindView(R.id.tv_title)
    TextView mLogoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
        mLogoView.setImageDrawable(getDrawable(R.mipmap.logo_butter_knife));
    }

    @OnClick(R.id.imgLogo)
    public void onBack() {
        this.onBackPressed();
    }

}