package cc.rememberme.demo.multithreading.task

/**
 * Task
 * @author guojialin
 * @since 2022-07-22 17:13
 */
class Task {

    private var executor: Executor = Executor()

    suspend fun start(intput: Int) {
        executor.execute(intput)
    }

}