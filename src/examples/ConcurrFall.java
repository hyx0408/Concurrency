package examples;

/**
 * Created by hubert on 2015.06.10.
 */
public class ConcurrFall {
    public static void main(String[] args) {
        Counter myCounter = new Counter();
        Integer myInt = 0;
        class MyThread extends Thread {
            int i = 0;

            MyThread(String s, int i) {
                super(s);
                this.i = i;
            }

            @Override
            public void run() {
                myCounter.add(i);
            }
        }
        MyThread[] myThreads = new MyThread[10];
        for (; myInt < 10; myInt++) {
            myThreads[myInt] = new MyThread("Thread" + myInt, myInt);
        }
        //wrong way show the sum
        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }
    }
}

class Counter {
    public int sum = 0;

    //should use synchronized to correct
    public int add(int i) {
        sum += i;//If you want something wrong, you should make this process more complicated.
        System.out.println(Thread.currentThread().getName() + " executed.");
        System.out.println("Sum is "+this.sum+".");
        return sum;
    }
}