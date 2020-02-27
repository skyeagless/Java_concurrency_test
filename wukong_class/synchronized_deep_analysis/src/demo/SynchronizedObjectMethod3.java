package demo;//方法锁

public class SynchronizedObjectMethod3 implements Runnable{
    private static SynchronizedObjectMethod3 instance = new SynchronizedObjectMethod3();

    public static void main(String[] args){
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
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
    // 每个类实例对应一把锁，每个synchronized方法都必须获得该类实例的锁（instance）才能执行，方法一旦执行，就会独占该锁
    public synchronized void method() throws InterruptedException {
        System.out.println("我是对象锁的方法修饰符形式,我叫"+ Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }
}
