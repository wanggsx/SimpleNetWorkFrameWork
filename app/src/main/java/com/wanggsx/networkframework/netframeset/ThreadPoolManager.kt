package com.wanggsx.networkframework.netframeset

import android.util.Log
import java.util.concurrent.*

class ThreadPoolManager private constructor() {

    //定义静态成员变量和静态方法
    companion object {

        private var mInstance: ThreadPoolManager =
            ThreadPoolManager()

        public fun getInstance(): ThreadPoolManager {
            return mInstance
        }
    }

    /** 不限制队列中Runnable对象的数量，最大数量为Integer.MAX_VALUE */
    var mQueueRunnables: LinkedBlockingDeque<Runnable> = LinkedBlockingDeque()


    /**
     参数解析：
        corePoolSize：   线程池维护线程的最少数量
        maximumPoolSize：线程池维护线程的最大数量
        keepAliveTime：  非核心线程能存活的最大时间,所允许的空闲时间
        unit：           线程池维护线程所允许的空闲时间的单位
        workQueue：      线程池所使用的缓冲队列
        handler：        线程池对拒绝任务的处理策略

      执行策略：
        当( 线程池中的数量 < corePoolSize ) 时，创建新的线程来处理被添加的任务；
        当( 线程池中的数量 == corePoolSize，且缓冲队列 workQueue未满 ) 时，新添加的Runnable任务被放入缓冲队列；
        当( maximumPoolSize > 线程池中的数量 > corePoolSize，且缓冲队列workQueue满 ) 时，开新的线程来处理新任务；
        当( 线程池中的数量 = maximumPoolSize > corePoolSize，缓冲队列workQueue满 ）时，执行RejectedExecutionHandler策略；
      * */
    var mExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        3,
        9,
        2L,
        TimeUnit.SECONDS,
        ArrayBlockingQueue<Runnable>(20),
        RejectedExecutionHandler {
            //当线程超过线程池最大数量时，会被拒绝执行，此时应该加入等待队列中
                r, executor ->
            //            addWaitingTask(r)
            Log.d("wanggsxnetwork", "RejectedExecutionHandler")
        }
    )

//    /** 添加线程到队列中 */
//
//    fun addWaitingTask(r : Runnable){
//        mQueueRunnables.add(r)
//    }
//
//    var executorRunnable : Runnable = Runnable {
//        var mRunnable : Runnable? = null
//        kotlin.run {
//            while (true){
//                mRunnable = mQueueRunnables.take()
//                mExecutor.execute(mRunnable!!)
//            }
//        }
//    }


}
