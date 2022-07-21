package cc.rememberme.demo.multithreading

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import cc.rememberme.demo.MainApplication
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 *
 * @author guojialin
 * @since 2022-07-21 14:27
 */
fun launchWithExpHandler(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = GlobalScope.launch(context + ExceptionHandler, start, block)


val ExceptionHandler by lazy {
    CoroutineExceptionHandler { _, throwable ->
        toast(throwable.message ?: "$throwable")
        throwable.printStackTrace()
    }
}


val mainHandler by lazy {
    Handler(Looper.getMainLooper())
}

inline fun runOnUi(noinline block: () -> Unit) {
    if (Looper.getMainLooper() == Looper.myLooper()) {
        block()
    } else {
        mainHandler.post(block)
    }
}


fun toast(m: String) =
    runOnUi {
        Toast.makeText(MainApplication.INS, m, Toast.LENGTH_SHORT).show()
    }

fun log(msg: String, tag: String = "gjl") =
    Log.d("gjl", msg)