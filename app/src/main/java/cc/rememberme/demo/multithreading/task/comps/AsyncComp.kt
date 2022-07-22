package cc.rememberme.demo.multithreading.task.comps

import cc.rememberme.demo.multithreading.launchWithExpHandler
import cc.rememberme.demo.multithreading.log
import cc.rememberme.demo.multithreading.task.Comp
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * 异步执行的组件
 * @author guojialin
 * @since 2022-07-22 17:39
 */
class AsyncComp : Comp() {

    private var job: Job? = null

    override suspend fun execute(input: Int, cache: MutableMap<String, String>): MutableMap<String, String> {
        super.execute(input, cache)

        log("work on Async Comp")

        runBlocking {
            launch {
                delay(5000)
                log("async job - done")
                continuation?.resume("async success", null)
            }
        }
        val result = callAndWait()
        cache["async_value"] = result

        return cache
    }

    override fun stop(): Boolean {
        return super.stop()
    }

    override fun pause(): Boolean {
        return super.pause()
    }

    override fun resume(): Boolean {
        return super.resume()
    }

    private var continuation: CancellableContinuation<String>? = null

    private suspend fun callAndWait(): String =
        suspendCancellableCoroutine { continuation ->
            this.continuation = continuation
            // Remove callback on cancellation
            continuation.invokeOnCancellation {
                it?.printStackTrace()
            }
            // At this point the coroutine is suspended by suspendCancellableCoroutine until callback fires
        }

}