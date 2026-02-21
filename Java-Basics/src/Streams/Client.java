package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] args) {

        List<Integer> list = List.of(5,4,1,10,6,9,2,8,7);

//        int minValue = Integer.MAX_VALUE;
//        for(int value : list) {
//            if(value < minValue) {
//                minValue = value;
//            }
//        }


        /*
        * Stream -> a pipeline of data
        * 1. we can not print a stream directly as it give a reference
        * 2. There are 2 type of methods in streams:-
        * a.) intermediate methods b.) terminal methods
        * intermediate methods are also returning reference and terminal methods return real outputs
        * on a single stream multiple intermediate methods can be allowed but only 1 terminal method is allowed.
        * a stream can be used only once, once it is used we cannot us it again.
        * we can apply intermediate method on a intermediate method and also a terminal method on a intermediate method
        * but once a terminal method is applied, we cannot use any method again.
        * */

//        System.out.println(list.stream()); //reference
//
//        System.out.println(list.stream().limit(6).count());
//        Stream<Integer> s1 = list.stream();
//        System.out.println(s1);
//        System.out.println(s1.limit(6).count());
//        System.out.println(s1.count());

        list.stream().limit(4).forEach(a-> System.out.println(a));

        List<Integer> evenList = list
                .stream()                       //stream conversion
                .filter(a-> a%2==0)     // intermediate method
                .collect(Collectors.toList());  // terminal method

        System.out.println(evenList);

        List<Integer> evenSquaredList = list
                .stream()
                .filter(a->a%2==0)
                .map(a->a*a)
                .collect(Collectors.toList());
        System.out.println(evenSquaredList);

        List<Integer> evenSquaredSortedAscendingList = list
                .stream()
                .filter(a->a%2==0)
                .map(a->a*a)
                .sorted((a,b)->a-b)
                .collect(Collectors.toList());

        System.out.println(evenSquaredSortedAscendingList);

        List<Integer> evenSquaredSortedDescendingList = list
                .stream()
                .filter(a->a%2==0)
                .map(a->a*a)
                .sorted((a,b)->b-a)
                .collect(Collectors.toList());

        System.out.println(evenSquaredSortedDescendingList);

        Integer sum = list
                .stream()
                .filter(a->a%2==0)
                .map(a->a*a)
                .sorted((a,b)->a-b)
                .reduce(0,(a,b)->a+b);
        System.out.println(sum);


        Integer minValue = list
                .stream()
                .filter(a->a%2==0)
                .map(a->a*a)
                .sorted((a,b)->a-b)
                .reduce(Integer.MAX_VALUE,(a,b)->Math.min(a,b));
        System.out.println(minValue);

        Integer maxValue = list
                .stream()
                .filter(a->a%2==0)
                .map(a->a*a)
                .sorted((a,b)->a-b)
                .reduce(Integer.MIN_VALUE,(a,b)->Math.max(a,b));
        System.out.println(maxValue);

        Optional<Integer> first = list
                .stream()
                .filter(a->a%2==0)
                .map(a->a*a)
                .sorted((a,b)->a-b)
                .findFirst();
        System.out.println(first.orElse(0));











//
    }
}
