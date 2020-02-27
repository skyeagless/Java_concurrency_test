package threadcoreknowledge.stopthreads;

//每次循环时都被sleep或wait时
public class RightStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try{
                while(num <= 10000){
                    if(num % 100 == 0){
                        System.out.println(num+"是100的倍数");
                    }
                    num++;
                    //前面执行时间很短，所以基本都在这个循环体里面
                    Thread.sleep(10);
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        //Main--sleep,时间为5000ms
        Thread.sleep(5000);
        thread.interrupt();
    }
}
