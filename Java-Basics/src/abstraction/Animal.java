package abstraction;

public interface Animal {

    public void eat();

    public void sleep();

    public void sound();

    public default void walk(){
        System.out.println("walking");
    }
}
