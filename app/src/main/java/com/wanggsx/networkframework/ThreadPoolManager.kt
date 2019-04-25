package com.wanggsx.networkframework

import java.util.concurrent.*

class ThreadPoolManager private constructor() {

    //定义静态成员变量和静态方法
    companion object {

        private var mInstance : ThreadPoolManager = ThreadPoolManager()

        public fun getInstance() : ThreadPoolManager {
            return mInstance
        }
    }

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
        30L, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(3),
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
