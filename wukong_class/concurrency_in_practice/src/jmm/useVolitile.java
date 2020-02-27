package jmm;

public class useVolitile implements Runnable{
    static volatile boolean done = false;
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
        }
    }

    private void setDone() {
        done = true;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new useVolitile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(done);
    }
}
