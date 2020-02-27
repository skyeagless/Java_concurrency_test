package deadlock;

//必定死锁
public class MustDeadLock implements Runnable{
    int flag = 1;
    static Object lock1 = new Object();
    static Object lock2 = new Object();
    @Override
    public void run() {
        System.out.println("flag="+flag);
        if(flag==1){
            synchronized (lock1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("1");
                }
            }

        }
        if(flag==0){
            synchronized (lock2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("0");
                }
            }
        }

    }

    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
