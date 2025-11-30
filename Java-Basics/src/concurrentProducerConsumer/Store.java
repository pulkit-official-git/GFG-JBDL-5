package concurrentProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Store {
    int maxSize;
    ConcurrentLinkedDeque<Object> items;
//    List<Object> items;

    public Store(int maxSize) {
        this.maxSize = maxSize;
        items = new ConcurrentLinkedDeque<>();
    }

    public void produceItem(){
        this.items.add(new Object());
        System.out.println(" Producer produced " + this.items.size() + " items");
    }

    public void consumeItem(){
        this.items.remove();
        System.out.println(" Consumer consumed " + this.items.size() + " items");
    }



}
