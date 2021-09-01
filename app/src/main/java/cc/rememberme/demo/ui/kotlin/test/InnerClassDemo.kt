package cc.rememberme.demo.ui.kotlin.test

/**
 *
 * @author : guojialin
 * @date : 2021-08-29 19:08
 */
class InnerClassDemo {

    class Teacher(var name: String) {
        fun show() = println("teacher names: $name")
    }

}


fun main() {
    InnerClassDemo.Teacher("Green").show()
}