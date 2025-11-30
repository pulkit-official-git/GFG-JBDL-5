package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {
//        ExecutorService ex = Executors.newFixedThreadPool(20);
        ExecutorService ex = Executors.newCachedThreadPool();
        for(int i = 1; i < 1000000; i++){
            if(i==8000){
                System.out.println(i);
            }
            PrintNumbers p = new PrintNumbers(i);
            ex.submit(p);
        }

    }
}
