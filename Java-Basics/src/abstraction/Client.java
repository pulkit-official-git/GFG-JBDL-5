package abstraction;

public class Client {

    public static void main(String[] args) {

        Animal a = new Dog();
        a.eat();
        a.sleep();
        a.sound();
//        a.run();
        a.walk();
        Dog d = new Dog();
        d.fly();
        User t = new Student();
        t.eat();
        t.dance();

    }
}
