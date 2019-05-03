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

    /** 这里要限制队列中Runnable对象的最大数量，否则会造成OOM（默认值为Integer.MAX_VALUE） */
    var mWattingQueueRunnables: LinkedBlockingDeque<Runnable> = LinkedBlockingDeque(20)


    /**
    参数解析：
    corePoolSize：   线程池维护线程的最少数量
    maximumPoolSize：线程池维护线程的最大数量
    keepAliveTime：  非核心线程能存活的最大时间,所允许的空闲时间
                     表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，
                     keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize.
                     即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。
    unit：           线程池维护线程所允许的空闲时间的单位
    workQueue：      线程池所使用的缓冲队列,一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响.
                     ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。
    handler：        线程池对拒绝任务的处理策略
                     ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
                     ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
                     ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
                     ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务

    执行策略：
    当( 线程池中的数量 < corePoolSize ) 时，创建新的线程来处理被添加的任务；
    当( 线程池中的数量 == corePoolSize，且缓冲队列 workQueue未满 ) 时，新添加的Runnable任务被放入缓冲队列；
    当( maximumPoolSize > 线程池中的数量 > corePoolSize，且缓冲队列workQueue满 ) 时，开新的线程来处理新任务；
    当( 线程池中的数量 = maximumPoolSize > corePoolSize，缓冲队列workQueue满 ）时，执行RejectedExecutionHandler策略；

     4个方法：
    execute()       向线程池提交一个任务，交由线程池去执行
    submit()        向线程池提交任务并返回任务执行的结果，底层也是调用了execute()方法
    shutdown()      关闭线程池，线程池处于SHUTDOWN状态，不接受新的任务，会等待所有任务执行完毕
    shutdownNow()   关闭线程池，线程池处于STOP状态，不接受新的任务，并且会尝试终止正在执行的任务
     * */
    var mExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        3,
        5,
        200L,
        TimeUnit.MILLISECONDS,
        mWattingQueueRunnables,
        ThreadPoolExecutor.CallerRunsPolicy()//把所有的任务都交由当前线程池来执行
    )

}
