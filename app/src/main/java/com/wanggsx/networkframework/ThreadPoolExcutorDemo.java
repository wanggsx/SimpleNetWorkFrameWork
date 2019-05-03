package com.wanggsx.networkframework;
import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcutorDemo {
    public static void main(String[] args) {
        //构造一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 200, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(5),new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=1;i<=12;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("创建第" + i + "个任务，线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }

     static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("开始执行task "+taskNum + " 被执行任务所在线程名:" + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
    }
}