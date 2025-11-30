package constructors;

public class Client {

    public static void main(String[] args) {

        Student student = new Student();
        student.id=1;
        System.out.println(student.id);

        Student s2 = new Student(2,"Joy",98.3,"47 street");
        Student s3 = s2;
        Student s4 = new Student();
        s4=s2;
        System.out.println(s3);
        System.out.println(s2);
        System.out.println(s4);

        Student s5 = new Student();
        s5.id= s2.id;
        s5.name=s2.name;
        s5.score=s2.score;
        s5.address = s2.address;
        System.out.println(s5);
//        Student s3 = new Student();
        Student s6 = new Student(s2);
        System.out.println(s6);


    }
}
