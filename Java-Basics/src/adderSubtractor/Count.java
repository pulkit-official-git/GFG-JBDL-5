package adderSubtractor;

public class Count {
    int value =0;

    public synchronized void incrementByX(int x){
        this.value+=x;
    }

    public synchronized void decrementByX(int x){
        this.value-=x;
    }
}
