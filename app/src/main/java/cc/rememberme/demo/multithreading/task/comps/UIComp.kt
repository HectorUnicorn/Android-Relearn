package cc.rememberme.demo.multithreading.task.comps

import cc.rememberme.demo.multithreading.log
import cc.rememberme.demo.multithreading.task.Comp
import cc.rememberme.demo.ui.coroutine.CoroutineTestActivity
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

/**
 * UI组件
 * @author guojialin
 * @since 2022-07-22 17:37
 */
class UIComp : Comp() {

    private var continuation: CancellableContinuation<String>? = null

    override suspend fun execute(
        input: Int,
        cache: MutableMap<String, String>
    ): MutableMap<String, String> {
        super.execute(input, cache)

        log("work on UI Comp")

        withContext(Dispatchers.Main) {
            CoroutineTestActivity.setListener {
                continuation?.resume("success", null)
            }
        }

        val result = callAndWait()
        cache["ui_value"] = result
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