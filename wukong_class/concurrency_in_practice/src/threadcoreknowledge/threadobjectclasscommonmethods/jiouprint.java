package threadcoreknowledge.threadobjectclasscommonmethods;

//奇数偶数交替打印
public class jiouprint {
   private static int count;
   private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread oushu = new Thread(() -> {
            while(count < 100){
                synchronized (lock){
                    if((count & 1) == 0){
                        System.out.println(Thread.currentThread().getName()+":"+count++);
                    }

                }
            }
        },"偶数线程");
        oushu.start();
        Thread jishu = new Thread(() -> {
            while(count < 100){
                synchronized (lock){
                    if((count & 1) == 1){
                        System.out.println(Thread.currentThread().getName()+":"+count++);
                    }

                }
            }
        },"奇数线程");
        jishu.start();
    }
}
