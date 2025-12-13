package exceptions;

import java.sql.SQLException;

public class Client {
    public static void main(String[] args) {

//        DivisionOperation d = new DivisionOperation();
//        System.out.println(d.operate(5,0));

        Student s = new Student();
        try{
//            throw new SQLException();
//            try{
//
//            }catch(Exception e){
//
//                try {
//
//                }catch(NullPointerException ex){
//
//                }
//
//            }
            s.findStudentByRollNo(51);
            throw new SQLException();
        }catch (ClassNotFoundException cnfe){
            System.out.println("cnfe");

        }catch(EvenException e) {
            System.out.println("even");

        }catch(SQLException sql){
            try{
                /*
                * crons for 3 retries
                * */
            }catch (Exception e){

            }

        }

    }
}
