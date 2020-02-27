package threadcoreknowledge.stopthreads.volatiledemo;

//volatile的局限：看似可行
public class WrongwayVolatile implements Runnable{
    private volatile boolean canceled = false;
    @Override
    public void run() {
        int num = 0;
        try{
            while(num <= 100000 && !canceled){
                if(num % 100 == 0){
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongwayVolatile r = new WrongwayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        r.canceled = true;
    }
}
