package cc.rememberme.demo.multithreading.task.comps

import cc.rememberme.demo.multithreading.log
import cc.rememberme.demo.multithreading.task.Comp

/**
 * 普通组件
 * @author guojialin
 * @since 2022-07-22 17:41
 */
class NormalComp : Comp() {

    override suspend fun execute(
        input: Int,
        cache: MutableMap<String, String>
    ): MutableMap<String, String> {
        super.execute(input, cache)
        log("work on normal comp")
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