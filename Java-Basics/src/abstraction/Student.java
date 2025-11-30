package abstraction;

public class Student extends User{
    @Override
    public void walk() {
        System.out.println("student is walking");
    }

    @Override
    public void sound() {
        System.out.println("student is sounding");
    }

    @Override
    public void eat() {
        System.out.println("student is eating");
    }

    @Override
    public void dance() {
        System.out.println("student is dancing");
    }
}
