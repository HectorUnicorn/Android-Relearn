package cc.rememberme.demo.ui.kotlin.test

/**
 * 懒初始化
 * @author : guojialin
 * @date : 2021-08-29 17:17
 */
class LazyBattlePlayer(_name: String) {

    /*
     * var是一个可变变量，这是一个可以通过重新分配来更改为另一个值的变量。这种声明变量的方式和java中声明变量的方式一样。
     * val是一个只读变量，这种声明变量的方式相当于java中的final变量。一个val创建的时候必须初始化，因为以后不能被改变。
     */
    var name = _name

    val config by lazy { loadConfig() }

    val equipment = loadEquipment()

    private val from : String
    private fun nameAge() = from[0]

    init {
        println(nameAge())
        from = "China"
    }

    private fun loadEquipment(): String {
        println("loading equipment...")
        return "Sword"
    }

    private fun loadConfig(): String {
        println("loading...")
        return "xxx"
    }

}

fun main() {
    val p = LazyBattlePlayer("Jack")
    Thread.sleep(3000)
    println(p.config)
    println(p.equipment)
}