package threadcoreknowledge.threadobjectclasscommonmethods;

public class jiouprint2 {
    private static int count = 0;
    private static final Object lock = new Object();

    static class TurningRunner implements Runnable{
        @Override
        public void run() {
            while(count <= 100){
                synchronized (lock){
                    //拿到锁就打印
                    System.out.println(Thread.currentThread().getName()+":"+ count++);
                    lock.notify();
                    if(count <= 100){
                        //让出锁，自己休眠
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TurningRunner()).start();
        new Thread(new TurningRunner()).start();
    }

}


