package collections;

import java.util.*;

public class Client {

    public static void main(String[] args) {

//        Integer [] a = {4,3,6,1,2,9,6,3};
//        Arrays.sort(a, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//        for(int i=0;i<a.length;i++){
//            System.out.println(a[i]);
//        }
//        System.out.println();

//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(2);
//        list.add(1);
//        list.add(7);
//        list.add(8);
//        list.add(2);
//        list.add(4);
//        System.out.println(list);
//
//        Collections.sort(list);
//
//
//        System.out.println(list);


        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Ram",93.67,18));
        students.add(new Student(2,"Sham",75.20,19));
        students.add(new Student(3,"Calm",93.67,18));
        students.add(new Student(4,"Nam",64.43,21));

//        Collections.sort(students);

//        for (Student student : students) {
//            System.out.println(student.id + " " + student.age + " " + student.name + " " + student.score);
//        }

//        Collections.sort(students,new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o2.score.compareTo(o1.score);
//            }
//        });

        Collections.sort(students,new StudentScoreComparator());

//        for (Student student : students) {
//            System.out.println(student.id + " " + student.age + " " + student.name + " " + student.score);
//        }


        List<Integer>list1 = new ArrayList<>(); //insertion order
        list1.add(5);
        list1.add(2);
        list1.add(1);
        list1.add(3);
        list1.add(4);
//        System.out.println(list1);

        List<Integer>list2 = new LinkedList<>(); //insertion order
        list2.add(5);
        list2.add(2);
        list2.add(1);
        list2.add(3);
        list2.add(4);
//        System.out.println(list2);

        List<Integer>list3 = new Vector<>(); //insertion order
        list3.add(5);
        list3.add(2);
        list3.add(1);
        list3.add(3);
        list3.add(4);
//        while (!list3.isEmpty()){
//            System.out.println(list3.getLast());
//            list3.removeLast();
//        }

        Stack<Integer>list4 = new Stack<>();
        list4.push(5);
        list4.push(2);
        list4.push(1);
        list4.push(3);
        list4.push(4);
//        while (!list4.empty()){
//            System.out.println(list4.peek());
//            list4.pop();
//        }

        Set<Integer> set1 = new HashSet<>(); // it gives ascending order
        set1.add(5);
        set1.add(2);
        set1.add(1);
        set1.add(3);
        set1.add(4);
        System.out.println(set1);

        TreeSet<Integer> set2 = new TreeSet<>();  // when size increases the order might differ but currently ascending
        set2.add(5);
        set2.add(2);
        set2.add(1);
        set2.add(3);
        set2.add(4);


        System.out.println(set2);

        LinkedHashSet<Integer> set3 = new LinkedHashSet<>();  //insertion order
        set3.add(5);
        set3.add(2);
        set3.add(1);
        set3.add(3);
        set3.add(4);
        System.out.println(set3);

        EnumSet<PaymentModes>enumSet =  EnumSet.of(PaymentModes.UPI,PaymentModes.COD);
        EnumSet<PaymentModes>enumSet1 =  EnumSet.range(PaymentModes.UPI, PaymentModes.COD);
        System.out.println(enumSet1);

        HashMap<Integer,String> hm = new HashMap();
//        hm.put("apple",123);
        hm.put(123,"mango");
        hm.put(456,"mango");

        System.out.println(hm);

        System.out.println(students);







    }
}
