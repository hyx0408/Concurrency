package examples;

/**
 * Created by hubert on 2015.06.11.
 */
public class WaitAndNotify {
    public static void main(String[] args) {
        WaitAndNotify myWaitAndNotify = new WaitAndNotify();
        MyRun myRunnable = new MyRun(myWaitAndNotify);
        Thread myThread = new Thread(myRunnable);
        myThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myWaitAndNotify.doNotify();
    }

    boolean needWait = true;

    void doWait() {
        synchronized (this.getClass()) {
            while (needWait) {
                try {
                    this.getClass().wait();
                } catch (InterruptedException e) {
                    System.out.println("I cannot wait!");
                }
                System.out.println("Now " + Thread.currentThread().getName() + " is waitting!");
            }
            needWait = true;
        }
    }

    void doNotify() {
        synchronized (this.getClass()) {
            this.getClass().notify();
            needWait = false;
        }
    }
}

class MyRun implements Runnable {
    WaitAndNotify wan;

    MyRun(WaitAndNotify wan) {
        this.wan = wan;
    }

    @Override
    public void run() {
        synchronized (wan.getClass()) {
            this.wan.doWait();
            System.out.println(Thread.currentThread().getName() + " was notified!");
        }
    }
}