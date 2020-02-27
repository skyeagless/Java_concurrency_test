package threadcoreknowledge.ThreadSafe.threaderrors;

//运行结果出错：计数不准确
public class ThreadsCountError implements Runnable{

    private int index = 0;
    boolean[] marked = new boolean[50000];
    private static ThreadsCountError r = new ThreadsCountError();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.index);
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
    }
}
