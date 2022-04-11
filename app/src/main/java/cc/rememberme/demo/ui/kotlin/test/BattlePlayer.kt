package cc.rememberme.demo.ui.kotlin.test

/**
 *
 * @author : guojialin
 * @date : 2021-08-29 17:05
 */
open class BattlePlayer(_name: String) {

    // 参数初始化
    var name = _name

    // 延迟初始化
    private lateinit var equipment: String

    fun init() {
        println("In ready()")
        equipment = "Sharp Knife"
    }

    open fun battle() {
        // 判断是否初始化
        // ::把方法当做一个参数
        if (::equipment.isInitialized) {
            println("fight use $equipment")
        } else {
            // 如果未执行ready()则走到此分支
            println("please equip player!!!")
        }
    }

}

fun main() {
    val p = BattlePlayer("Kitty")
    // 需执行初始化
    p.init()
    // 这里才会有值
    p.battle()
}