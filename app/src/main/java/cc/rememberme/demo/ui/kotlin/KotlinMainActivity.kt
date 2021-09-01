package cc.rememberme.demo.ui.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import cc.rememberme.demo.R
import cc.rememberme.demo.base.activity.BaseActivity

open class KotlinMainActivity : BaseActivity() {

    companion object {
        /*
         *  startActivity(Intent(MainActivity@this, SecondActivity::class.java))
         *  或者
         *  startActivity(Intent(this, SecondActivity::class.java))
         */
        @JvmStatic
        fun launch(activity: Activity, title: String) {
            // Kotlin 中 双冒号操作符 表示把一个方法当做一个参数，传递到另一个方法中进行使用，通俗的来讲就是引用一个方法。先来看一下例子：
            val intent = Intent(activity, KotlinMainActivity::class.java)
            intent.putExtra(KEY_TITLE, title)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotliin_main)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Hi from kotlin", Toast.LENGTH_LONG).show()
    }
}