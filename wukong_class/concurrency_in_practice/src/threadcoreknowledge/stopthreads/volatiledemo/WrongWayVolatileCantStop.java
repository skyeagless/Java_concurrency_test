package threadcoreknowledge.stopthreads.volatiledemo;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//演示volatile的局限:陷入阻塞时，volatile是无法结束线程的
//生产者生产速度很快，消费者消费速度较慢，出现了阻塞队列满了，生产者会阻塞等待消费者消费
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Productor productor = new Productor( storage);
        Thread producerThread = new Thread(productor);
        producerThread.start();
        //塞满队列
        Thread.sleep(2000);
        Consumer consumer = new Consumer(storage);
        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(1000);
        }
        System.out.println("消费者不需要更多数据了");
        //线程长时间阻塞，volitile失去作用
//        productor.canceled = true;
        producerThread.interrupt();
        System.out.println(productor.canceled);
    }

}

class Productor implements Runnable{
    public volatile boolean canceled = false;
    BlockingQueue storage;
    public Productor(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try{
//            while(num <= 100000 && !canceled){
              while(num <= 100000 && !Thread.currentThread().isInterrupted()){
                if(num % 100 == 0){
                    //阻塞点，不会去判断!canceled
                    storage.put(num);
                    System.out.println(num + "是100的倍数，被放到了仓库里面");
                }
                num++;
                Thread.sleep(1);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        if(Math.random() > 0.95){
            return false;
        }
        return true;
    }
}
