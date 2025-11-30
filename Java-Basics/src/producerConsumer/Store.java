package producerConsumer;

import java.util.ArrayList;
import java.util.List;

public class Store {
    int maxSize;
    List<Object> items;

    public Store(int maxSize) {
        this.maxSize = maxSize;
        items = new ArrayList<>();
    }

    public void produceItem(){
        this.items.add(new Object());
        System.out.println(" Producer produced " + this.items.size() + " items");
    }

    public void consumeItem(){
        this.items.remove(this.items.size() - 1);
        System.out.println(" Consumer consumed " + this.items.size() + " items");
    }



}
