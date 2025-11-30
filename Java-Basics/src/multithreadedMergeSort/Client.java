package multithreadedMergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();
        List<Integer> list = List.of(6,5,3,7,9,1,10,2);
        Sorter sorter = new Sorter(list,ex);
        Future<List<Integer>> ans =ex.submit(sorter);
        List<Integer> result = ans.get();
        System.out.println(result);
        ex.shutdown();

    }
}
