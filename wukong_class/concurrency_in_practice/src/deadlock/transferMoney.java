package deadlock;

//两人转账遇到死锁
public class transferMoney implements Runnable{
    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);


    public static void main(String[] args) throws InterruptedException {
        transferMoney r1 = new transferMoney();
        transferMoney r2 = new transferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a的余额"+a.balance);
        System.out.println("b的余额"+b.balance);
    }


    @Override
    public void run() {
        if(flag == 1){
            transfer(a,b,200);
        }
        if(flag == 0){
            transfer(b,a,200);
        }
    }

    static void transfer(Account from, Account to, int amount) {
        class Helper{
            public void transfer_help(){
                if(from.balance - amount < 0){
                    System.out.println("余额不足，转账失败");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("成功转账"+amount+"元");
            }
        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if(fromHash > toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer_help();
                }
            }
        }
        else{
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer_help();
                }
            }
        }
    }

    static class Account{
        int balance;
        public Account(int balance) {
            this.balance = balance;
        }
    }


}
