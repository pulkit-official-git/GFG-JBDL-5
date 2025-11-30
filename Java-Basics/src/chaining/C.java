package chaining;

public class C extends B{
    C(String name){
//        super(10);
        System.out.println("C is printing "+name);
    }
    C(){
        System.out.println("C is printing");
    }
}
