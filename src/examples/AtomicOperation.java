package examples;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hubert on 2015.06.15.
 */
public class AtomicOperation {
    public static void main(String[] args) {
        AtomicBoolean locked = new AtomicBoolean(false);
        System.out.println(locked.compareAndSet(false, true));
    }
}
