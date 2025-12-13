package lamdas;

import java.util.*;

public class Client {

    public static void main(String[] args) {

//        way1
//        Task task = new Task();
//        Thread t = new Thread(task);
//        t.start();

//        way2
//        Runnable r = ()->{
//            System.out.println("hello");
//            System.out.println("hello ,moto");
//        };
//        Thread t = new Thread(r);
//        t.start();

//        way3
//        Thread t = new Thread(()->{
//            System.out.println("hello");
//        });
//        t.start();




        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Ram",93.67,18));
        students.add(new Student(2,"Sham",75.20,19));
        students.add(new Student(3,"Calm",93.67,18));
        students.add(new Student(4,"Nam",64.43,21));

//        for (Student student : students) {
//            System.out.println(student.id + " " + student.age + " " + student.name + " " + student.score);
//        }

//        way1
//        Collections.sort(students,new StudentScoreComparator());

//        way2
//        Collections.sort(students, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.age-o2.age;
//            }
//        });

//        way3
        Collections.sort(students,(Student a,Student b)->{return a.age-b.age;});

//        way4
        Collections.sort(students,(a,b)->{return a.name.compareTo(b.name);});

//        way5
        Collections.sort(students,(a,b)-> a.age-b.age);

//         way6
        Collections.sort(students,Student::compareTo); // this syntax can only be used id the method is already defined



        MathematicalOperator mo = new Addition();
        System.out.println(mo.operate(10,20));

        MathematicalOperator mo2 = (a,b)->{return a/b;};
        System.out.println(mo2.operate(20,10));

        MathematicalOperator mo3 = (a,b)->a-b;
        System.out.println(mo3.operate(30,10));

//        (/*Its is compulsory for more than 1 variable and optional for single variable*/)->{/*its optional for 1 line and if method in
//        functional interface is returning then return is compulsory*/}

    }
}
