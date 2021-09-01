package cc.rememberme.demo.ui.kotlin.test

/**
 *
 * @author : guojialin
 * @date : 2021-08-29 17:05
 */
open class BattlePlayer(_name: String) {

    var name = _name
    // 延迟初始化
    private lateinit var equipment: String

    fun ready() {
        equipment = "Sharp Knife"
    }

    open fun battle() {
        if (::equipment.isInitialized) {
            println("fight use $equipment")
        } else {
            println("please equip player!!!")
        }
    }

}

fun main() {
    val p = BattlePlayer("Kitty")
//    p.ready()
    p.battle()
}