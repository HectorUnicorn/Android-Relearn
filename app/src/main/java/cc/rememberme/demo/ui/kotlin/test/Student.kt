package cc.rememberme.demo.ui.kotlin.test

/**
 * 测试构造函数执行顺序
 * @author : guojialin
 * @date : 2021-08-29 16:40
 */
class Student(
    _name: String,
    val age: Int  // 【1】默认构造函数执行 1
) {
    var name = _name // 2 // 【2】类级别属性赋值
    var score = 10 // 3
    private val hobby = "music" // 4
    val subject: String // 8

    init {
        // 【3】初始化块
        println("3. initializing student... $name") // 5
        subject = "math" // 8
    }

    // 【4】构造函数重载 / 次构造函数
    constructor(_name: String) : this(_name, 10) {
        println("4. constructor... $score") // 5
        score = 20
    }

}

fun main() {
    Student("Jack")
}