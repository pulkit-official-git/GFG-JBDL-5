package adderSubtractor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Count c = new Count();
        Lock lock = new ReentrantLock();
        ExecutorService ex = Executors.newCachedThreadPool();
        Future<Integer> f1 = ex.submit(new Adder(c,lock));
        Future<Integer> f2 = ex.submit(new Subtractor(c,lock));
        f1.get();
        f2.get();
        System.out.println(c.value);



    }
}
