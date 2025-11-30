package PrintNumbers;

public class Client {

    public static void main(String[] args) {
//        for (int i = 0; i < 10000; i++) {
//            PrintNumbers pn = new PrintNumbers(i);
//            if(i==800){
//                System.out.println(i);
//            }
//            Thread t1 = new Thread(pn, "Thread " + i);
//            t1.start();
//        }
        for (int i = 0; i < 10000; i++) {
            if(i%2==0){
                Thread even = new Thread(new PrintNumbers(i),"Even Thread");
                even.start();
            }
            else{
                Thread odd = new Thread(new PrintNumbers(i),"Odd Thread");
                odd.start();
            }
        }

    }
}
