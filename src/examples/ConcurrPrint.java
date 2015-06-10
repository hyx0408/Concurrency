package examples;

/**
 * Created by hubert on 2015.06.10.
 */
public class ConcurrPrint {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread("Thread" + i) {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            }.start();
        }
    }
}
