package examples;

/**
 * Created by hubert on 2015.06.11.
 */
public class ThreadLocal {
    static class MyRun implements Runnable{
        private java.lang.ThreadLocal<String> myThreadLocal =
                new java.lang.ThreadLocal<>();
        @Override
        public void run() {
            myThreadLocal.set(Thread.currentThread().toString());
            System.out.println(myThreadLocal.get());
        }
    }
    public static void main(String[] args) {
        MyRun myRunnable = new MyRun();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();
    }
}
