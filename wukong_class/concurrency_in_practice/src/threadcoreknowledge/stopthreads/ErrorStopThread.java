package threadcoreknowledge.stopthreads;

//错误的停止方法：用stop()停止线程，会导致线程运行一半突然停止，无法完成一个基本单位（连队）的操作，会产生脏数据
public class ErrorStopThread implements Runnable{
    @Override
    public void run() {
        //模拟五个10人的连队
        try{
            for(int i = 0; i < 5;i++){
                System.out.println("连队"+ i +"开始领取武器");
                for(int j = 0; j<10; j++){
                    System.out.println(j);
                    Thread.sleep(50);
                }
                System.out.println("连队"+ i +"领取武器完毕");
            }
        }catch(InterruptedException e){
            System.out.println("保存日志");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Thread thread =  new Thread(new ErrorStopThread());
       thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.stop();
        thread.interrupt();
    }
}
