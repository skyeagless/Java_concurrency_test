package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//每秒钟输出当前时间，被中断观察
public class SleepInterrupted implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(6000);
        thread.interrupt();
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 10; i++) {
                System.out.println(new Date());
                TimeUnit.SECONDS.sleep(1);
            }
        }catch(InterruptedException e){
            System.out.println("被中断");
            e.printStackTrace();
        }
    }
}
