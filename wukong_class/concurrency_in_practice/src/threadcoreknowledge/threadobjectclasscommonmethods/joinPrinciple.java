package threadcoreknowledge.threadobjectclasscommonmethods;

//join的代替写法
public class joinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        //run方法结束之后有一个隐藏的notify
        Thread thread1 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
        });
        thread1.start();

        System.out.println("开始等待子线程运行完毕");
//        thread1.join();
        synchronized (thread1){
            thread1.wait();
        }

        //主线程等待子线程执行完毕
        System.out.println("所有子线程执行完毕");
    }
}
