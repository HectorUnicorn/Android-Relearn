package cc.rememberme.demo.ui.kotlin.test

/**
 * object用法
 * @author : guojialin
 * @date : 2021-08-29 18:55
 */
object ObjectSingletonDemo {

    init {
        println("initializing object demo")
    }

    fun doSomething() {
        println("do something")
    }

}

fun main() {
    ObjectSingletonDemo.doSomething()

    //
    val pSingle = object : BattlePlayer("Singleton") {
        // 重写方法
        override fun battle() {
            println("IN p class battle()")
        }
    }

    println(pSingle.battle())
}