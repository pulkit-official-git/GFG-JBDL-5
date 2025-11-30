package chaining;

public class B extends A{
    B(int a){
        System.out.println("B is printing"+a);
    }
    B(int a,int b){
        System.out.println("B is printing"+a+b);
    }
    B(){
        System.out.println("B is printing");
    }
}
