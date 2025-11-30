package concurrentProducerConsumer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {
    Store store;
    Lock lock;
    Semaphore producerSemaphore;
    Semaphore consumerSemaphore;
    public Consumer(Store store, Lock lock, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
        this.store = store;
        this.lock = lock;
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
    }

    @Override
    public void run() {
        while (true) {
//            lock.lock();
            try {
                this.consumerSemaphore.acquire();
                if(this.store.items.size() > 0) {
                    this.store.consumeItem();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.producerSemaphore.release();


//            lock.unlock();
        }

    }
}
