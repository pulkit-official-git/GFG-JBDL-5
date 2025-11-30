package multithreadedMergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Sorter implements Callable<List<Integer>> {
    List<Integer> noToSort;
    ExecutorService ex;

    public Sorter(List<Integer> noToSort,ExecutorService ex) {
        this.noToSort = noToSort;
        this.ex = ex;
    }

    @Override
    public List<Integer> call() throws Exception {

        if(noToSort.size()<=1){
            return noToSort;
        }

        int mid = noToSort.size()/2;

        List<Integer> left =new ArrayList<>();
        List<Integer> right =new ArrayList<>();
        for(int i=0;i<mid;i++){
            left.add(noToSort.get(i));
        }

        for(int i=mid;i<noToSort.size();i++){
            right.add(noToSort.get(i));
        }
        Sorter sorter = new Sorter(left,ex);
        Sorter sorter2 = new Sorter(right,ex);
        Future<List<Integer>>leftArray = ex.submit(sorter);
        Future<List<Integer>>rightArray = ex.submit(sorter2);
        left = leftArray.get();
        right = rightArray.get();
        int i=0,j=0;
        List<Integer> result=new ArrayList<>();
        while(i<left.size()&&j<right.size()){
            if(left.get(i)<=right.get(j)){
                result.add(left.get(i++));
            }
            else{
                result.add(right.get(j++));
            }
        }
        while(i<left.size()){
            result.add(left.get(i++));
        }
        while(j<right.size()){
            result.add(right.get(j++));
        }
        return result;

    }
}
