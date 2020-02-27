package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//lock本身就是需要本身释放的
public class sleepdontreleaselock implements Runnable{
    private static final Lock lock = new ReentrantLock();
    @Override
    public void run(){
        lock.lock();
        System.out.println("线程"+Thread.currentThread().getName()+"获得到了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程"+Thread.currentThread().getName()+"已被唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        sleepdontreleaselock r = new sleepdontreleaselock();
        new Thread(r).start();
        new Thread(r).start();
    }
}
