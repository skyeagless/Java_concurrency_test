package threadcoreknowledge.threadobjectclasscommonmethods;

public class CurrentThread implements Runnable{
    public static void main(String[] args) {
        //run()：执行线程是主线程，不会用子线程调用
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
