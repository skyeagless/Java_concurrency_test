package com.skyeagle.concurrency.aqs;
//加入参数的CountDOWNLatch
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest2 {
    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(
                    ()->{
                        try {
                            test(threadNum); //子线程
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            countDownLatch.countDown();
                        }
                    }
            );
        }
        try {
            countDownLatch.await(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish");
        executorService.shutdown();
    }
    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1);
        System.out.println(threadNum);
    }

}
