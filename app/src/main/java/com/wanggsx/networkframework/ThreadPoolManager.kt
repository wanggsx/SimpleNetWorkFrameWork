package com.wanggsx.networkframework

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.concurrent.*

class ThreadPoolManager  {

    var mInstance = ThreadPoolManager()

    private fun ThreadPoolManager(){}

    /** 不限制队列中Runnable对象的数量，最大数量为Integer.MAX_VALUE */
    var mQueueRunnables : LinkedBlockingDeque<Runnable> = LinkedBlockingDeque()

    /**
     * public ThreadPoolExecutor(int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue)
     *
     * */
    var mExecutor : ThreadPoolExecutor = ThreadPoolExecutor(3,9,
        30, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(3),
        RejectedExecutionHandler {
            //当线程超过线程池最大数量时，会被拒绝执行，此时应该加入等待队列中
                r, executor ->
            addWaitingTask(r)
        })

    /** 添加线程到队列中 */

    fun addWaitingTask(r : Runnable){
        mQueueRunnables.add(r)
    }

    var executorRunnable : Runnable = Runnable {

        var mRunnable : Runnable? = null

        kotlin.run {
            while (true){
                mRunnable = mQueueRunnables.take()
                mExecutor.execute(mRunnable!!)
            }
        }

    }



}
