package genericsInheritance;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Util.doSomething(new Cat());
        Util.doSomething(new Dog());
        Util.doSomething(new Animal());

        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog());
        animals.add(new Cat());

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        dogs.add(new Dog());

        Util.doingSomething(animals);
        Util.doingSomething2(dogs);
    }
}
