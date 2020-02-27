package com.skyeagle.concurrency.aqs;

import java.util.concurrent.*;

public class FutureTest {
    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("do something in callable");
            Thread.sleep(5000);
            return "ok";
        }
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        System.out.println("do other things in main");
        Thread.sleep(1000);
        System.out.println(future.get());
    }
}
