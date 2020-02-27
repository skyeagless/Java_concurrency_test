package demo;

//类锁的第一种形式，static形式(方法）
public class SynchornizedClassStatic4 implements Runnable{
    private static SynchornizedClassStatic4 instance1 = new SynchornizedClassStatic4();
    private static SynchornizedClassStatic4 instance2 = new SynchornizedClassStatic4();

    public static void main(String[] args){
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()){}
        System.out.println("finished");
    }

    @Override
    public void run() {
        try {
            method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void method() throws InterruptedException {
        System.out.println("我是类锁的第一种形式:static,我叫"+ Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }
}
