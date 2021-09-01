package cc.rememberme.demo.ui.kotlin.test

import kotlin.math.absoluteValue

/**
 *
 * @author : guojialin
 * @date : 2021-08-29 07:46
 */
public class Player(
    var name: String,
    var age: Int = 20,
    var isNormal: Boolean
) {

    //
    constructor(name: String) : this(name, age = 10, isNormal = false)

    constructor(name: String, age: Int) : this(name, age, isNormal = false) {
        this.name = name.toUpperCase();
    }

    // 原始写法
//    var name = _name
//        get() = field.capitalize()
//        set(value) {
//            field = value.trim()
//        }
//
//    var age = _age
//        get() = age.absoluteValue
//        set(value) {
//            field = age.absoluteValue
//        }
//
//    var isNormal = _isNormal

    // 初始化块可以设置变量或值，
    init {
        require(age > 0) { "age must be positive" }
        require(name.isNotBlank()) { "player must have a name" }
    }
}

fun main() {
    val p = Player("Jack", 20, true)
    p.name = "rose"
    println(p.name)

    val p2 = Player("Rosie");
    println("name: ${p2.name} - age: ${p2.age}")

    val p3 = Player("Jakie", 20)
    println(p3.name)

//    val p4 = Player(isNormal = false, _name = "Jack")
}