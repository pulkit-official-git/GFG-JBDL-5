package PrintNumbers;

public class PrintNumbers implements Runnable {
    int noToPrint;

    public PrintNumbers(int noToPrint) {
        this.noToPrint = noToPrint;
    }

    @Override
    public void run() {
        System.out.println(noToPrint + " using Thread " + Thread.currentThread().getName());

    }
}
