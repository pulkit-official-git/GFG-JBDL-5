package helloWorldThread;

public class PrintHelloWorld implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Hello World");
    }
}
