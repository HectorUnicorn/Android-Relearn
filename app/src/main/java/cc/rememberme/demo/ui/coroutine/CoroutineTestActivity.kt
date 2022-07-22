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
import cc.rememberme.demo.multithreading.task.Task
import cc.rememberme.demo.multithreading.toast
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

/**
 * 协程相关逻辑测试
 * @author guojialin
 * @since 2022-03-16 15:58
 */
public class CoroutineTestActivity : BaseActivity() {


    companion object {
        @JvmStatic
        fun launch(ctx: Activity) {
            val intent = Intent(ctx, CoroutineTestActivity::class.java)
            intent.putExtra(KEY_TITLE, "协程测试");
            ctx.startActivity(intent)
        }

        private var listener: View.OnClickListener? = null

        fun setListener(l: View.OnClickListener) {
            listener = l
        }
    }

    var binding: ActivityCoroutineBinding? = null

    var job: Job? = null
    var subJob: Job? = null
    var subsubJob: Job? = null


    var job2: Job? = null
    var subJob2: Job? = null
    var subsubJob2: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)
        toast("Hi FROM CTA")
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btnStartCoroutine -> {
                log("=== start coroutine")
                job = launchWithExpHandler {
//                    testMultiJob3()
//                    val result = callAndWait()
//                    log("result:$result")
                    val task = Task()
                    task.start(1)
                }
            }

            R.id.btnMiddleAction -> {
//                continuation?.resume("Done", null)
                job?.cancel(null)
                log("==call cancel")
            }

            R.id.btnEndCoroutine -> {
//                cancelMultiJob()
//                job?.cancel(CancellationException("Error"))
                listener?.onClick(v)
            }

            R.id.btnStartCoroutine2 -> {
                log("=== start coroutine")
                job = launchWithExpHandler {

                    withContext(Dispatchers.Main) {
                        log("0:" + Thread.currentThread().name)
                        someFuncs(object : ICallback {
                            override fun onResult(msg: String) {
                                GlobalScope.launch {
                                    withContext(Dispatchers.Default) {
                                        log("1:$msg:" + Thread.currentThread().name)
                                        job?.cancel(null)
                                        if (this.isActive) {
                                            someFuncs2(object : ICallback {
                                                override fun onResult(msg: String) {
                                                    GlobalScope.launch {
                                                        withContext(Dispatchers.Default) {
                                                            log("2:$msg:" + Thread.currentThread().name)
                                                            someFuncs3()
                                                        }
                                                    }
                                                }
                                            })
                                        }
                                    }
                                }
                            }
                        })
                    }
                }
            }

            R.id.btnMiddleAction2 -> {
//                continuation?.resume("Done", null)
                mCallback?.onResult("Hi")
            }

            R.id.btnEndCoroutine2 -> {
//                cancelMultiJob()
                job?.cancel(CancellationException("Error"))
            }
        }
    }


    private var continuation: CancellableContinuation<String>? = null

    private fun testMultiJob3() = runBlocking {
        with(Dispatchers.Main) {
            log("start")
            log("remote response")
        }
    }

    private suspend fun callAndWait(): String =
        suspendCancellableCoroutine { continuation ->
            this.continuation = continuation
            // Remove callback on cancellation
            continuation.invokeOnCancellation {
                it?.printStackTrace()
            }
            // At this point the coroutine is suspended by suspendCancellableCoroutine until callback fires
        }

    private fun testMultiJob2() {
        val a: String = runBlocking {
            log("--- job1 launch：" + Thread.currentThread().name)
            launch {
                log("--- job2 launch：" + Thread.currentThread().name)
                delay(3000)
                log("--- job2 done")
            }
            delay(2000)
            log("--- job1 done")
            "hi"
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
        job2 = launchWithExpHandler {
            println("--- 1.job launch：" + Thread.currentThread().name)
        }
    }


    suspend fun fetchDoc() {
        val result = get("https://developer.android.com")
//        show(result)
    }

    suspend fun get(url: String) = withContext(Dispatchers.IO) {
        /* ... */
    }


    private var mCallback: ICallback? = null

    fun someFuncs(callback: ICallback) {
        log("someFuncscalled:" + Thread.currentThread().name)
        mCallback = callback
    }

    fun someFuncs2(callback: ICallback) {
        log("someFuncs2 called:" + Thread.currentThread().name + " " + job?.isCompleted)
        mCallback = callback
    }

    fun someFuncs3() {
        log("someFuncs3 called:" + Thread.currentThread().name + " " + job?.isCompleted)
        log("Hello")
    }

    interface ICallback {
        fun onResult(msg: String)
    }

}