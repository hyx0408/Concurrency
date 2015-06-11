package examples;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hubert on 2015.06.11.
 */
public class LockReentrance {
    ReentrantLock myLock = new ReentrantLock();
    public static void main(String[] args) {
        new LockReentrance().outPrint();
    }
    void outPrint(){
        myLock.lock();
        System.out.println("I am outPrint.");
        innerPrint();
        myLock.unlock();
    }
    void innerPrint(){
        myLock.lock();
        System.out.println("I am innerPrint.");
        myLock.unlock();
    }
}
