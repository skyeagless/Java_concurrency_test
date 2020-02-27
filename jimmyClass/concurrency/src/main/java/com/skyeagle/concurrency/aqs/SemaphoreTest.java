package com.skyeagle.concurrency.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private final static int threadCount = 200;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(
                    ()->{
                        try {
                            semaphore.acquire();
                            test(threadNum);
                            semaphore.release();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
            );
        }
        executorService.shutdown();
        System.out.println("finish");
    }
    private static void test(int threadNum) throws InterruptedException {
        System.out.println(threadNum);
        Thread.sleep(1000);
    }

}

