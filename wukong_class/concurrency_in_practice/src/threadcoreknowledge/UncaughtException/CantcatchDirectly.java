package threadcoreknowledge.UncaughtException;

/**
 * 描述：1.不加try catch 抛出4个异常，都带线程名字
 * 2.加了try catch,期望捕获到第一个线程的异常，线程234不应运行，希望看到打印出的e.
 * 3.结果：没有Caught Exception,线程234仍然运行并且抛出异常
 * */
public class CantcatchDirectly implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        try{
            //发生异常的位置是在子线程上，try-catch只能执行线程内异常
            new Thread(new CantcatchDirectly(),"MyThread-1").start();
            Thread.sleep(1000);
            new Thread(new CantcatchDirectly(),"MyThread-2").start();
            Thread.sleep(1000);
            new Thread(new CantcatchDirectly(),"MyThread-3").start();
            Thread.sleep(1000);
            new Thread(new CantcatchDirectly(),"MyThread-4").start();
        }catch(RuntimeException e){
            //程序无法到达这里，无法捕获这个异常
            System.out.println("caught exception");
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
