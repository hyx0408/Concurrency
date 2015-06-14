package examples;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hubert on 2015.06.14.
 */
public class BlockingQueue {

    public static void main(String[] args) {
        class Producer implements Runnable {
            LinkedBlockingQueue<String> ldq = null;

            Producer(LinkedBlockingQueue<String> ldq) {
                this.ldq = ldq;
            }

            @Override
            public void run() {
                Integer i = (int) (100 * Math.random());
                String s = i.toString();
                if (ldq.offer(s)) {
                    System.out.println(Thread.currentThread().getName() + " offers " + s +
                            " and now queue have " + ldq.size() + " numbers.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " ----Queue is full.");
                }
            }
        }
        class Consumer implements Runnable {
            LinkedBlockingQueue<String> ldq = null;

            Consumer(LinkedBlockingQueue<String> ldq) {
                this.ldq = ldq;
            }

            @Override
            public void run() {
                String s = ldq.poll();
                if (s != null) {
                    System.out.println(Thread.currentThread().getName() + " gets " + s + " and now queue have" +
                            ldq.size() + " numbers.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " ----Queue is empty.");
                }
            }
        }
        LinkedBlockingQueue<String> myLBQ = new LinkedBlockingQueue<>(3);
        Producer producer = new Producer(myLBQ);
        Consumer consumer = new Consumer(myLBQ);
        Thread[] myProducers = new Thread[6];
        for (int i = 0; i < 6; i++) {
            myProducers[i] = new Thread(producer, "Producer" + i);
        }
        Thread[] myConsumers = new Thread[6];
        for (int i = 0; i < 6; i++) {
            myConsumers[i] = new Thread(consumer, "Consumer" + i);
        }
        for (Thread temp : myProducers) {
            temp.start();
        }
        for (Thread temp : myConsumers) {
            temp.start();
        }
    }
}
