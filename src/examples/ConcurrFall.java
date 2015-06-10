package examples;

/**
 * Created by hubert on 2015.06.10.
 */
public class ConcurrFall {
    public static void main(String[] args) {
        class Counter {
            public int sum = 0;
            //should use synchronized to correct
            public int add(int i) {
                sum += i;//If you want something wrong, you should make this process more complicated.
                return sum;
            }
        }
        Counter myCounter = new Counter();
        class MyThread extends Thread {
            int i = 0;

            MyThread(String s, int i) {
                super(s);
                this.i = i;
            }

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " executed." );
                myCounter.add(this.i);
            }
        }
        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread("Thread" + i, i);
        }
        //wrong way show the sum
        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
            System.out.println("Sum is "+ myCounter.sum+".");
        }


    }
}
