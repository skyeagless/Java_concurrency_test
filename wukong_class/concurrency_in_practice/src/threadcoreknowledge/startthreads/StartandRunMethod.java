package threadcoreknowledge.startthreads;

//对比start()和run()启动线程的方式
public class StartandRunMethod {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        runnable.run();
        new Thread(runnable).start();
    }
}
