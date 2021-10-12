package cc.rememberme.demo.ui.hummer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.didi.hummer.HummerActivity;
import com.didi.hummer.adapter.navigator.NavPage;
import com.didi.hummer.context.HummerContext;
import com.didi.hummer.core.engine.JSCallback;
import com.didi.hummer.core.engine.JSValue;
import com.didi.hummer.core.engine.base.ICallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * for test hummer
 * https://hummer.didi.cn/doc#/zh-CN/android_doc
 *
 * @author guojialin
 * @since 2021-10-08 19:45
 */
public class HummerSinglePageActivity extends HummerActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, HummerSinglePageActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected NavPage getPageInfo() {
        return super.getPageInfo();
//        return new NavPage("HelloWorld6.js");
//        return new NavPage("/sdcard/test/HelloWorld6.js");
    }


    @Override
    protected void renderHummer() {
        // 使用Intent传入的url渲染JS页面
        super.renderHummer();

        // 使用固定assets文件渲染JS页面
        // hmRender.renderWithUrl("HelloWorld.js");

        // 使用固定url渲染JS页面
        hmRender.renderWithUrl("http://172.21.196.131:8000/index.js");
    }

    @Override
    protected void onPageRenderFailed(@NonNull Exception e) {
        Log.e("zdf", "onPageRenderFailed, " + Log.getStackTraceString(e));
    }


}
