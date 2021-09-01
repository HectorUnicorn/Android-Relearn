package cc.rememberme.demo.ui.kotlin.test

import java.io.File

/**
 * object的伴生作用
 * @author : guojialin
 * @date : 2021-08-29 19:04
 */
open class CompanianObjectDemo {

    companion object {
        private const val PATH = "/root/user/hector"

        fun load() = File(PATH).readBytes()

    }

}

fun main () {
    // kotlin static的实现，不调用不会实例化
    CompanianObjectDemo.load()
}