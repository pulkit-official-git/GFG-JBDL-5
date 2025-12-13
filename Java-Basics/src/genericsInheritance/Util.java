package genericsInheritance;

import java.util.List;

public class Util {

    public static void doSomething(Animal animal) {

    }

    public static void doingSomething(List<Animal> animals) {
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Animal());
    }

    public static void doingSomething2(List<? extends Animal> animals) {
//        animals.add(new Dog());
//        animals.add(new Cat());
//        animals.add(new Animal());
//        then u cannot do generic things
    }

    public static <T extends Animal>void doingSomething3(List<T> animals) {
//        animals.add(new Dog());
//        animals.add(new Cat());
//        animals.add(new Animal());
//        then u cannot do generic things
    }



}
