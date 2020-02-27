package threadcoreknowledge.threadobjectclasscommonmethods;

public class joininterrupted {
    public static void main(String[] args) {
        Thread mainthread = Thread.currentThread();
        Thread thread = new Thread(()->{
            try {
                //中断主线程
                mainthread.interrupt();
                Thread.sleep(5000);
                System.out.println("Thread1 finished");
            } catch (InterruptedException e) {
                System.out.println("子线程中断");
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        //主线程等待的时候被中断
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"主线程中断了");
            //join中断后传递
            thread.interrupt();
            e.printStackTrace();
        }
    }
}
