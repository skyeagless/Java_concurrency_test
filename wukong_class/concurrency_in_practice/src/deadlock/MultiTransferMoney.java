package deadlock;

import java.util.Random;
import deadlock.transferMoney.Account;


//多人同时转账，仍然很危险
public class MultiTransferMoney {
    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATIONS = 1000000;
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random rnd = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length ; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }
        class TranferThread implements Runnable{
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAccount = rnd.nextInt(NUM_ACCOUNTS);
                    int toAccount = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(NUM_MONEY);
                    transferMoney.transfer(accounts[fromAccount],accounts[toAccount],amount);
                }
            }
        }
        for(int i = 0; i< NUM_THREADS;i++){
            new Thread(new TranferThread()).start();
        }
    }








}
