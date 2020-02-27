package deadlock;

//哲学家就餐问题导致的死锁
public class DiningPhilospophers {
    public static class Philosopher implements Runnable{
        private Object leftChops;
        private Object rightChops;

        public Philosopher(Object leftChops, Object rightChops) {
            this.leftChops = leftChops;
            this.rightChops = rightChops;
        }

        @Override
        public void run() {
            try{
                while(true){
                    doAction("Thinking");
                    synchronized (leftChops){
                        doAction("pickup left chops");
                        synchronized (rightChops){
                            doAction("pickup right chops -- eating");
                            doAction("Put down right chops");
                        }
                        doAction("Put down left chops");
                    }
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName()+" "+action);
            Thread.sleep((long) (Math.random()*10));
        }
    }

    public static void main(String[] args) {
        //哲学家数组
        Philosopher[] philosophers = new Philosopher[5];
        //筷子数组
        Object[] chopsticks = new Object[philosophers.length];
        //筷子实例化
        for (int i = 0; i < chopsticks.length ; i++) {
            chopsticks[i] = new Object();
        }
        //哲学家实例化
        for(int i=0;i<philosophers.length;i++){
            Object leftChops = chopsticks[i];
            Object rightChops = chopsticks[(i+1)%chopsticks.length];
            if( i == philosophers.length-1){
                philosophers[i] = new Philosopher(rightChops,leftChops);
            }else{
                philosophers[i] = new Philosopher(leftChops,rightChops);
            }
            //启动线程
            new Thread(philosophers[i],"哲学家"+(i+1)+"号").start();
        }
    }

}
