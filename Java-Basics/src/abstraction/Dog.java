package abstraction;

public class Dog implements Animal, Flying {
    @Override
    public void eat() {
        System.out.println("eating");
    }

    @Override
    public void sleep() {
        System.out.println("sleeping");
    }

    @Override
    public void sound() {
        System.out.println("bhow bhow");
    }

    public void run(){
        System.out.println("running");
    }

    public void walk(){
        System.out.println("dog is walking");
    }

    @Override
    public void fly() {
        System.out.println("flying");
    }
}
