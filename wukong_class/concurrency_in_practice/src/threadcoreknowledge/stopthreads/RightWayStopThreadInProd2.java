package threadcoreknowledge.stopthreads;

//catch子语句中调用Thread.currentThread().interrupted()来恢复设置中断状态，以便在后续的
//执行中，依然能够检查到刚才发生的中断
public class RightWayStopThreadInProd2 implements Runnable{
    //顶层函数只能处理，不能抛出了
    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted,程序运行结束");
                break;
            }
            reInterrupt();
        }
    }

    //捕获异常，并重新设置中断
    private void reInterrupt(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

