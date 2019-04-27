package com.wanggsx.networkframework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}

class Producer implements Runnable{

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            queue.put("1");
            Thread.sleep(2000);
            queue.put("2");
            Thread.sleep(2000);
            queue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            takeOne();
            takeOne();
            takeOne();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void takeOne() throws InterruptedException{
        System.out.println("startTake " + System.currentTimeMillis());
        System.out.println(queue.take() + " " + System.currentTimeMillis());
    }
}


