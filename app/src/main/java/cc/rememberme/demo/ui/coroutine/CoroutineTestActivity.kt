package cc.rememberme.demo.ui.coroutine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import cc.rememberme.demo.R
import cc.rememberme.demo.base.activity.BaseActivity
import cc.rememberme.demo.databinding.ActivityCoroutineBinding
import cc.rememberme.demo.multithreading.launchWithExpHandler
import cc.rememberme.demo.multithreading.log
import cc.rememberme.demo.multithreading.toast
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 协程相关逻辑测试
 * @author guojialin
 * @since 2022-03-16 15:58
 */
public class CoroutineTestActivity : BaseActivity() {

    var binding: ActivityCoroutineBinding? = null

    var job: Job? = null
    var subJob: Job? = null
    var subsubJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)
        toast("Hi FROM CTA")
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btnStartCoroutine -> {
                Logger.d("=== start coroutine")
//                testMultiJob()
                testMultiJob2()
            }
            R.id.btnEndCoroutine -> {
                cancelMultiJob()
            }
        }
    }

    private fun testMultiJob2() {
        runBlocking {
            log("--- job1 launch：" + Thread.currentThread().name)
            launch {
                log("--- job2 launch：" + Thread.currentThread().name)
                delay(3000)
                log("--- job2 done")
            }
            delay(2000)
            log("--- job1 done")
        }
    }


    private fun cancelMultiJob() {
        job?.cancel()
        job?.children?.iterator()?.forEach {
            println("--- x1.job children:$it")
        }
        println("--- x1.job after cancel:" + job?.isCancelled + " / " + subJob?.isCancelled + " / " + subsubJob?.isCancelled)
        //                subJob?.cancel()
        //                println("--- x2.job after cancel" + job?.isCancelled + " / " + subJob?.isCancelled + " / " + subsubJob?.isCancelled)
        //                subsubJob?.cancel()
        //                println("--- x3.job after cancel" + job?.isCancelled + " / " + subJob?.isCancelled + " / " + subsubJob?.isCancelled)
    }


    private fun testMultiJob() {
        job = launchWithExpHandler {

            println("--- 1.job launch：" + Thread.currentThread().name)

            delay(3000)

            subJob = launchWithExpHandler {

                println("--- 2.job in sub job launch" + Thread.currentThread().name)

                delay(3000)

                subsubJob = launchWithExpHandler {

                    println("--- 3.job in subsubjob launch" + Thread.currentThread().name)

                    delay(3000)
                }
                delay(3000)

            }
            delay(15000)
        }
    }


    suspend fun fetchDoc() {
        val result = get("https://developer.android.com")
//        show(result)
    }

    suspend fun get(url: String) = withContext(Dispatchers.IO) {
        /* ... */
    }

    companion object {
        @JvmStatic
        fun launch(ctx: Activity) {
            val intent = Intent(ctx, CoroutineTestActivity::class.java)
            intent.putExtra(KEY_TITLE, "协程测试");
            ctx.startActivity(intent)
        }
    }

}