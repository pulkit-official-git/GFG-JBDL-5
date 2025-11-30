package adderSubtractor;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class Subtractor implements Callable<Integer> {
    Count count;
    Lock lock;

    public Subtractor(Count c,Lock lock) {
        this.count = c;
        this.lock = lock;
    }

    @Override
    public Integer call() throws Exception {
//        lock.lock();
//        synchronized (count) {
            for (int i = 0; i <= 100000; i++) {
//                this.count.value-=i;
                this.count.decrementByX(i);
            }
//        }

//        lock.unlock();
        return this.count.value;
    }
}
