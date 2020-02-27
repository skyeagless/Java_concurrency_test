package com.skyeagle.concurrency.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something in callable");
                Thread.sleep(5000);
                return "ok";
            }
        });
        new Thread(futureTask).start();
        System.out.println("do other things in main");
        Thread.sleep(1000);
        System.out.println(futureTask.get());
    }
}
