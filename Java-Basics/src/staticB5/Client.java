package staticB5;

public class Client {

    public static void main(String[] args) {

        Student student = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        student.setId(1);
        student.setName("<UNK>");
        System.out.println(student);
        System.out.println(Student.organisation);
//        student2.organisation = "NoGfg";
        System.out.println(Student.organisation);
    }
}
