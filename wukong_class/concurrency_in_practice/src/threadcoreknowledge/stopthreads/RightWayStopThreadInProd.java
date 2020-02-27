package threadcoreknowledge.stopthreads;

//catch InterruptedException之后，优先选择throw异常
//那么在run()就会强制try/catch

public class RightWayStopThreadInProd implements Runnable{
    //顶层函数只能处理，不能抛出了
    @Override
    public void run() {
        while(true){
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志，停止程序");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
