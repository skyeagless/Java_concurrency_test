package threadcoreknowledge.threadobjectclasscommonmethods;
/**
* 3个线程，线程1和2首先被阻塞，线程3唤醒它们
 * start先执行并不代表线程先启动
* */
public class waitNofifyAll implements Runnable{
    private static final Object resourceA = new Object();
    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName()+" got resourceA lock");
            System.out.println(Thread.currentThread().getName()+" waits to start");
            try {
                resourceA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" is waiting to end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new waitNofifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(() -> {
           synchronized (resourceA){
               resourceA.notifyAll();
               System.out.println("ThreadC nofified");
           }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(500);
        threadC.start();
    }
}
