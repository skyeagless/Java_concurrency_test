package jmm;

public class NoVolatile implements Runnable{
    static volatile int a;
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new NoVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(a);
    }
}
