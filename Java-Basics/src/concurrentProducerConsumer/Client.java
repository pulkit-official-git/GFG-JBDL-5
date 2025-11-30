package concurrentProducerConsumer;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    public static void main(String[] args) {
        Store store = new Store(5);
        Semaphore producerSemaphore = new Semaphore(5);
        Semaphore consumerSemaphore = new Semaphore(0);
        Lock lock = new ReentrantLock();
        ExecutorService ex = Executors.newCachedThreadPool();


        for(int i =0;i<10;i++){
            ex.submit(new Producer(store,lock,producerSemaphore,consumerSemaphore));
        }

        for(int i =0;i<8;i++){
            ex.submit(new Consumer(store,lock,producerSemaphore,consumerSemaphore));
        }


    }
}
