package cc.rememberme.demo.multithreading.task.comps

import cc.rememberme.demo.multithreading.log
import cc.rememberme.demo.multithreading.task.Comp
import kotlinx.coroutines.delay

/**
 * 延时执行Task
 * @author guojialin
 * @since 2022-07-22 17:36
 */
class TimingComp : Comp() {

    private var retryTimes = 3

    override suspend fun execute(
        input: Int,
        cache: MutableMap<String, String>
    ): MutableMap<String, String> {
        super.execute(input, cache)

        log("work on Timing Comp")

        while (retryTimes > 0) {
            delay(2000)
            retryTimes--
            log("retry left time: $retryTimes")
        }

        cache["retryTimes"] = retryTimes.toString()

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

}