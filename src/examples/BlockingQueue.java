package examples;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by hubert on 2015.06.14.
 */
public class BlockingQueue {
    static class Producer implements Runnable {
        java.util.concurrent.BlockingQueue<String> ldq = null;

        Producer(java.util.concurrent.BlockingQueue<String> ldq) {
            this.ldq = ldq;
        }

        @Override
        public void run() {
            Integer i = (int) (100 * Math.random());
            String s = i.toString();
            synchronized (ldq) {
                if (ldq.offer(s)) {
                    System.out.println(Thread.currentThread().getName() + " offers " + s +
                            " and now queue have " + ldq.size() + " numbers.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " ----Queue is full.");
                }
            }
        }
    }

    static class Consumer implements Runnable {
        java.util.concurrent.BlockingQueue<String> ldq = null;

        Consumer(java.util.concurrent.BlockingQueue<String> ldq) {
            this.ldq = ldq;
        }

        @Override
        public void run() {
            String s = ldq.poll();
            synchronized (ldq) {
                if (s != null) {
                    System.out.println(Thread.currentThread().getName() + " gets " + s + " and now queue have " +
                            ldq.size() + " numbers.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " ----Queue is empty.");
                }
            }
        }
    }

    public static void main(String[] args) {
        java.util.concurrent.BlockingQueue<String> myLBQ = new PriorityBlockingQueue<>(3);
        //new ArrayBlockingQueue<>(4);
        //new LinkedBlockingQueue<>(4);
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
