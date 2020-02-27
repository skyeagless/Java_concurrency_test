package threadcoreknowledge.threadobjectclasscommonmethods;

public class SleepDontReleaseMonitor implements Runnable{

    @Override
    public void run() {
        try {
            syn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void syn() throws InterruptedException {
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了monitor");
        Thread.sleep(5000);
        System.out.println("线程"+Thread.currentThread().getName()+"退出了同步代码块");
    }

    public static void main(String[] args) {
        SleepDontReleaseMonitor r = new SleepDontReleaseMonitor();
        new Thread(r).start();
        new Thread(r).start();

    }
}
