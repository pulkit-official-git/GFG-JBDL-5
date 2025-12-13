package collections;

import org.jetbrains.annotations.NotNull;


public class Student implements Comparable<Student> {
    Integer id;
    String name;
    Double score;
    Integer age;

//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", score=" + score +
//                ", age=" + age +
//                '}';
//    }

    public Student(Integer id, String name, Double score, Integer age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public Student() {

    }

    @Override
    public int compareTo(@NotNull Student o) {
        return this.name.compareTo(o.name);
    }
}
