package threadcoreknowledge.sixstates;

//展示线程的New,Runnable,Terminated状态,即使是正在运行，也是runnable状态
public class NewRunnableTerminated implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(6);
        //运行中
        System.out.println(thread.getState());
        Thread.sleep(500);
        //运行后
        System.out.println(thread.getState());
    }
}
