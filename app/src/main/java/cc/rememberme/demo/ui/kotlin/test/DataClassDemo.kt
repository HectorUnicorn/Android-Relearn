package cc.rememberme.demo.ui.kotlin.test

/**
 * Data Class
 * @author : guojialin
 * @date : 2021-09-23 08:43
 *
 * data会有默认实现的toString、equals、hashCode方法；还提供了一个copy函数，相当于clone
 */
data class DataClassDemo(var x: Int, var y: Int, var name: String = "123") {

    val isInBounds = x > 0 && y > 0

}

fun main() {
    // DataClassDemo(x=10, y=20) 数据类型提供了toString的个性化实现
    println(DataClassDemo(10, 20))

    // 如果去掉data是不相等的，因为没有实现equals；加上data就有equals的默认实现
    println(DataClassDemo(10, 20).equals(DataClassDemo(10, 20)))

    // true
    // == 是比较内容，默认用equals，如果没有实现，则fallback到Any的equals实现，就是 ===
    // === 是比较引用
    println(DataClassDemo(10, 20) == (DataClassDemo(10, 20)))

    // false，是两个对象
    println(DataClassDemo(10, 20) === (DataClassDemo(10, 20)))

    // 每个参数都会加入到equals的实现当中； false
    println(DataClassDemo(10, 20, "876").equals(DataClassDemo(10, 20, "543")))
}