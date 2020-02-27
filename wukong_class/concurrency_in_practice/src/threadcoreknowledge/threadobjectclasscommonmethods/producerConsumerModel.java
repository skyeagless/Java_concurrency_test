package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.LinkedList;
import java.util.Random;

//wait,notify来实现生产者--消费者模式
public class producerConsumerModel {
    public static void main(String[] args) {
        int maxSize = 100;
        LinkedList<Integer> storage = new LinkedList<>();
        EventStorage eventStorage = new EventStorage(maxSize,storage);
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }



    static class Producer implements Runnable{
        private EventStorage eventStorage;

        Producer(EventStorage eventStorage) {
            this.eventStorage = eventStorage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                try {
                    eventStorage.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        private EventStorage eventStorage;

        Consumer(EventStorage eventStorage) {
            this.eventStorage = eventStorage;
        }
        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                try {
                    eventStorage.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class EventStorage{
        private int maxSize;
        private LinkedList<Integer> storage;

        public EventStorage(int maxSize, LinkedList<Integer> storage) {
            this.maxSize = maxSize;
            this.storage = storage;
        }

        public synchronized void put() throws InterruptedException {
            while(storage.size() == maxSize){
                wait();
            }
            storage.add(new Random().nextInt());
            System.out.println("仓库里有了"+storage.size()+"产品");
            //唤醒消费者
            notify();
        }

        public synchronized void take() throws InterruptedException{
            while(storage.size() == 0){
                wait();
            }
            System.out.println("拿到了"+storage.poll()+",现在仓库还剩下"+storage.size());
            //通知生产者
            notify();
        }
    }

}
