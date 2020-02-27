package threadcoreknowledge.stopthreads;

//带有sleep中断线程的写法
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try{
                while(num <= 300){
                    if(num % 100 == 0){
                        System.out.println(num+"是100的倍数");
                    }
                    num++;
                }
                //while完成之后就睡眠（内部sleep)
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        //Main--sleep,时间为500ms说明子线程在睡眠的时候被中断
        Thread.sleep(500);
        thread.interrupt();
    }
}
