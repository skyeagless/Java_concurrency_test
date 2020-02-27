package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//用tryLock避免死锁
public class tryLockDeadLock implements Runnable{
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    int flag = 1;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(flag == 1){
                try {
                    if(lock1.tryLock(800, TimeUnit.MICROSECONDS)){
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock2.tryLock(800,TimeUnit.MICROSECONDS)){
                            System.out.println("线程1获取两把锁");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }else{
                            System.out.println("线程1获取锁2失败，已重试");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程1获取锁1失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(flag == 0){
                try {
                    if(lock2.tryLock(3000, TimeUnit.MICROSECONDS)){
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock1.tryLock(3000,TimeUnit.MICROSECONDS)){
                            System.out.println("线程2获取两把锁");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        }else{
                            System.out.println("线程2获取锁1失败，已重试");
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程2获取锁2失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        tryLockDeadLock r1 = new tryLockDeadLock();
        tryLockDeadLock r2 = new tryLockDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
