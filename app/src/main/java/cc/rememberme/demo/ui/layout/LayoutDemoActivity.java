package cc.rememberme.demo.ui.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import cc.rememberme.demo.base.activity.BaseActivity;
import cc.rememberme.demo.databinding.ActivityLayoutDemoBinding;

/**
 * @author : guojialin
 * @date : 2021/8/8 14:28
 */
public class LayoutDemoActivity extends BaseActivity {


    private ActivityLayoutDemoBinding layoutBinding;

    private ScrollView layoutScrollView;

    /**
     * 通过使用像@NotNull和@Nullable之类的annotation来声明一个方法是否是空指针安全的。现代的编译器、IDE或者工具可以读此annotation
     * 并帮你添加忘记的空指针检查，或者向你提示出不必要的乱七八糟的空指针检查。IntelliJ和findbugs已经支持了这些annotation。
     * 这些annotation同样是JSR 305的一部分，但即便IDE或工具中没有，这个annotation本身可以作为文档。
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityLayoutDemoBinding.inflate(LayoutInflater.from(this));
        setContentView(layoutBinding.getRoot());
        initViews();
    }

    private void initViews() {
        layoutScrollView = layoutBinding.layoutScrollView;

    }

}
