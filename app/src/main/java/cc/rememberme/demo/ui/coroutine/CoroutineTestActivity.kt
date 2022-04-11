package cc.rememberme.demo.ui.coroutine

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import cc.rememberme.demo.databinding.ActivityCoroutineBinding
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 协程相关逻辑测试
 * @author guojialin
 * @since 2022-03-16 15:58
 */
public class CoroutineTestActivity : AppCompatActivity() {


    var binding: ActivityCoroutineBinding? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)

        binding?.btnStartCoroutine?.setOnClickListener {
            Logger.d("=== start coroutine")

        }

    }


    suspend fun fetchDoc() {
        val result = get("https://developer.android.com")
//        show(result)
    }

    suspend fun get(url: String) = withContext(Dispatchers.IO) {
        /* ... */
    }

}