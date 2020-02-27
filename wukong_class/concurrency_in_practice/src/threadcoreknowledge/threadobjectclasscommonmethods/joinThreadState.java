package threadcoreknowledge.threadobjectclasscommonmethods;

//先join后获取状态
public class joinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(3000);
                System.out.println(mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread-0运行结束");
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }

}
