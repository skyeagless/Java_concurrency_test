package threadcoreknowledge.stopthreads;


//如果while里面放try-catch，中断就会失效
//java中sleep函数catch完异常后，就会将isInterruptedd()标记位清零，所以这个循环不能退出
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            while(num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if(num % 100 == 0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        //Main--sleep,时间为5000ms
        Thread.sleep(5000);
        thread.interrupt();
    }

}
