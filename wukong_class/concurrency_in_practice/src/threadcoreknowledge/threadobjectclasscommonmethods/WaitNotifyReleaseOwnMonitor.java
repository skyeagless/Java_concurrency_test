package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 描述:证明wait只释放当前锁
 * */
public class WaitNotifyReleaseOwnMonitor {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (resourceA){
                System.out.println("ThreadA got resourceA lock.");
                synchronized (resourceB){
                    System.out.println("ThreadA got resourceB lock.");
                    try {
                        System.out.println("ThreadA releases resourceA lock.");
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread thread2 = new Thread(()->{
            synchronized (resourceA){
                System.out.println("ThreadB got resourceA lock");
                synchronized (resourceB){
                    System.out.println("ThreadB got resourceB lock");
                }
            }
        });

        thread1.start();
        Thread.sleep(2000);
        thread2.start();
    }
}
