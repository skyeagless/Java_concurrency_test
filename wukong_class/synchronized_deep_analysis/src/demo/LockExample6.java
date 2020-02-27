package demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample6 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
    }
}
