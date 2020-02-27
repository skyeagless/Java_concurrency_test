package com.skyeagle.concurrency.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    private static CyclicBarrier barrier = new CyclicBarrier(5);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10 ; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(
                    ()->{
                        try {
                            race(threadNum);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        executorService.shutdown();
    }
    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
       Thread.sleep(1000);
       System.out.println(threadNum +" is ready");
       barrier.await();
       System.out.println(threadNum + " 继续执行");
    }
}
