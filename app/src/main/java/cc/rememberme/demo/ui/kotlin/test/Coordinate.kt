package cc.rememberme.demo.ui.kotlin.test

/**
 * 坐标数据类型
 * @author : guojialin
 * @date : 2021-08-29 19:11
 */
data class Coordinate(var lang: Int, var lat: Int) {

    val isInBounds = lang > 0 && lat > 0

}

fun main() {
    println(Coordinate(10, 20))
    // == 比较内容，equals
    // === 比较引用 | 如果equals未实现则，fallback到Any 默认实现 ===， 比较引用
    // data类会自动覆盖equal方法
    println(Coordinate(10, 20) == Coordinate(10, 20))
}