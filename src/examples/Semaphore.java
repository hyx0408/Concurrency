package examples;

/**
 * Created by hubert on 2015.06.11.
 */
public class Semaphore {
    public static void main(String[] args) {
        java.util.concurrent.Semaphore mySemaphore = new java.util.concurrent.Semaphore(3);
        class consumer implements Runnable {
            @Override
            public void run() {
                if (mySemaphore.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " consume one.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " cannot get one.");
                }
            }
        }
        Thread[] myConsumers = new Thread[4];
        Runnable myRun = new consumer();
        for (int i = 0; i <4 ; i++) {
            myConsumers[i] = new Thread(myRun,"Consumer"+i);
        }
        for (Thread temp : myConsumers) {
            temp.start();
        }
    }
}
