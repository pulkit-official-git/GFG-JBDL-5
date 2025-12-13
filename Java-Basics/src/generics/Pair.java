package generics;

//Class level generics
public class Pair <A,B> {

    A first;
    B second;

    public void setB(B b){
        this.second=b;
    }

    public A getA(){
        return this.first;
    }

//    generics are defined at object creation but static is a part of class not object
//    so you need to define method level generics for static methods
    public static<A> void didSomething(A a){
        System.out.println(a);
    }

//    if you define a generic at a class level and again if u define same generic at method level,
//    then the method level generic will override the class level generic
    public <A> void doSomething(A a){
        System.out.println(a);
    }

//    Method Level Generics
    public <Z>void doingSomething(Z a){
        System.out.println(a);
    }
}
