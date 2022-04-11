package cc.rememberme.demo.ui.kotlin.test

/**
 * copy
 * @author : guojialin
 * @date : 2021-09-24 07:51
 */
data class DataCopyClassDemo(var name: String, val age: Int) {
    private val hobby = "music"
    val subject: String

    init {
        println("initializing...")
        subject = "math"
    }

    constructor(_name: String) : this(_name, 10)

    override fun toString(): String {
        return "DataCopyClassDemo(name='$name', age=$age, hobby='$hobby', subject='$subject')"
    }

}

fun main() {
    val s = DataCopyClassDemo("Jack")
    // data toString只会打印主构造函数的属性：DataCopyClassDemo(name=Rose, age=10)
    println(s)
    val copyObj: DataCopyClassDemo = s.copy("Rose")
    println(copyObj)
}