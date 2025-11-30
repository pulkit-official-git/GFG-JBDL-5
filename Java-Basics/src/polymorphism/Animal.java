package polymorphism;

public class Animal {
    String name;

    public void eat(){
        System.out.println("eats nothing");
    }
    public void eat(String name){
        System.out.println("eats " + name);
    }

    public void eat(int count){
        System.out.println("eats " + count);
    }

    public void eat(int count,String name){
        System.out.println("eats " + count + " " + name);
    }
}
