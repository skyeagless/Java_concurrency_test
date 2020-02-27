package com.skyeagle.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//信号量、countDownlatch的并发模拟[线程不安全]
public class ConcurrencyTest {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    public static int count = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //semaphore控制并发的线程数，是synchronized的扩展形式【停车场模型】
        final Semaphore semaphore = new Semaphore(threadTotal);
        //countDownLatch用于阻塞一个线程，等待其他线程先后到达某个条件的时候，再执行这个线程的后续操作
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try{
                    //获取许可
                    semaphore.acquire();
                    add();
                    //访问完后，释放
                    semaphore.release();
                } catch(Exception e){
                    e.printStackTrace();
                }
                //在这个整数“倒数”到0之前，主线程需要等待在门口
                countDownLatch.countDown();
            });
        }
        System.out.println("main thread await");
        try {
            //调用await方法后，使当前线程在锁存器(内部计数器)倒计数至零之前一直等待，进入休眠状态，除非线程被中断。
            //如果当前计数递减为零，则此方法立即返回，继续执行。
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread finishes await");
        executorService.shutdown();
        System.out.println(count);
    }
    private static void add(){count++;}
}
