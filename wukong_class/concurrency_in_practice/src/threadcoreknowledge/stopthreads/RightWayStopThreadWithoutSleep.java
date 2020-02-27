package threadcoreknowledge.stopthreads;

//run方法内没有sleep或wait方法时，停止线程
public class RightWayStopThreadWithoutSleep implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while(!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE/2){
            if(num % 10000 == 0){
                System.out.println(num+"是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }

    //Thread.sleep()是Thread类的一个静态方法，使当前线程休眠，进入阻塞状态（暂停执行），如果线程在睡眠状态被中断，将会抛出IterruptedException中断异常
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        //休眠的是main线程，所以这一秒内RightWayStopThreadWithoutSleep类线程运行
        Thread.sleep(1000);
        thread.interrupt();
    }
}


