package constructors;

public class Student {

    int id;

    String name = "AK";

    double score;

    String address;

    Student(int id, String name, double score, String address) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.address = address;
    }

    Student() {
        this.id = 0;
        this.name = "AK";
        this.score = 98.3;
    }

    Student(String address,String name) {
        this.address = address;
        this.name = name;
    }

    Student(Student s) {
        this.id = s.id;
        this.name = s.name;
        this.score = s.score;
        this.address = s.address;
    }


}
