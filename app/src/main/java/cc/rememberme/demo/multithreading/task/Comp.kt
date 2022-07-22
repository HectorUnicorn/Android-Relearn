package cc.rememberme.demo.multithreading.task

import cc.rememberme.demo.multithreading.log

/**
 * 组件
 * @author guojialin
 * @since 2022-07-22 17:14
 */
open class Comp {

    open suspend fun execute(input: Int, cache: MutableMap<String, String>): MutableMap<String, String> {
        log("==execute input:$input cache:$cache in  ${Thread.currentThread().name}")
        return mutableMapOf<String, String>()
    }

    open fun stop(): Boolean {
        log("==stop in ${Thread.currentThread().name}")
        return true
    }

    open fun pause(): Boolean {
        log("==pause in ${Thread.currentThread().name}")
        return true
    }

    open fun resume(): Boolean {
        log("==pause in ${Thread.currentThread().name}")
        return true
    }

}