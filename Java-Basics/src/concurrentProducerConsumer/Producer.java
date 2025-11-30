package concurrentProducerConsumer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {
    Store store;
    Lock lock;
    Semaphore producerSemaphore;
    Semaphore consumerSemaphore;
    public Producer(Store store, Lock lock, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
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
                this.producerSemaphore.acquire();
                if(this.store.items.size() < this.store.maxSize) {
                    this.store.produceItem();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.consumerSemaphore.release();
//            lock.unlock();
        }

    }
}
