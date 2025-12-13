package generics;

public class Client {

    public static void main(String[] args) {

//        do we have a compile time check?
        Pair<String,Long> p = new  Pair<String,Long>();
        p.first="USA";
//        p.first="ram";
        p.second=100L;
        System.out.println(p.first+" "+p.second);
//        p.second=200;
        Pair<String,Long> p2 = new  Pair</*optional*/>();

        System.out.println(p.getA());
        p.setB(1000L);

        System.out.println(p.first+" "+p.second);
        p.doSomething("apple");

        p.doingSomething(100L);
        p.<Double>doingSomething(68.23);

        p.doSomething("apple");
        p.doSomething(100L);

        Pair.didSomething(100);
        Pair.didSomething("apple");

    }
}
