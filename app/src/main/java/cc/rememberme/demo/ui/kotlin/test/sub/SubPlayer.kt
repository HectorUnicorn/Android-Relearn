package cc.rememberme.demo.ui.kotlin.test.sub

import cc.rememberme.demo.ui.kotlin.test.BattlePlayer
import java.io.File

/**
 *
 * @author : guojialin
 * @date : 2021-08-29 18:34
 */
class SubPlayer : BattlePlayer("Hector") {

    override fun battle() {
        println("$name is fighting")
    }

    fun special() = "Hi, this is special"

}

fun main() {
    val p: BattlePlayer = SubPlayer()
    p.battle()
    println(p is BattlePlayer)
    println(p is SubPlayer)
    println(p is Any)
    println(p)

    // 子类和父类转换
    if (p is SubPlayer) {
        val sp = (p as SubPlayer)
        println(p.special())
    }

}