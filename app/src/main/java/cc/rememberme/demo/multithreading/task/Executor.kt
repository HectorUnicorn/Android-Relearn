package cc.rememberme.demo.multithreading.task

import cc.rememberme.demo.multithreading.log
import cc.rememberme.demo.multithreading.task.comps.AsyncComp
import cc.rememberme.demo.multithreading.task.comps.NormalComp
import cc.rememberme.demo.multithreading.task.comps.TimingComp
import cc.rememberme.demo.multithreading.task.comps.UIComp

/**
 * 执行器
 * @author guojialin
 * @since 2022-07-22 17:13
 */
class Executor {

    private var shouldStop = false

    private val comps =
        mutableListOf<Comp>(NormalComp(), TimingComp(), UIComp(), AsyncComp(), NormalComp())

    private var currentComp: Comp? = null

    suspend fun execute(input: Int) {
        log("executor start")
        var currentStep = 0
        var cache: MutableMap<String, String> = mutableMapOf<String, String>()
        while (!shouldStop) {
            currentComp = comps[currentStep++]
            cache = currentComp?.execute(input + currentStep, cache)
                .orEmpty() as MutableMap<String, String>
            if (currentStep >= comps.size) {
                log("executor done")
                shouldStop = true
            }
        }
        log("final result:$cache")
    }
}