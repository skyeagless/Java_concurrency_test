package demo;

//对象锁---代码块形式
public class SynchronizedObjectCodeBlock2 implements Runnable{
    private static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();
    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {
        //串行执行

        //lock1
        synchronized (lock1) {
            System.out.println("我是对象锁lock1的代码块形式，我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"lock1运行结束");
        }
        //lock2
        synchronized (lock2) {
            System.out.println("我是对象锁lock2的代码块形式，我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"lock2运行结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()){}
        System.out.println("finished");
    }
}
