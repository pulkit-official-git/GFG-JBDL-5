package helloWorldThread;

public class Client {

    public static void doSomething(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        PrintHelloWorld printHelloWorld = new PrintHelloWorld();
        Thread thread = new Thread(printHelloWorld,"first thread");
        thread.start();
        doSomething();
    }
}
