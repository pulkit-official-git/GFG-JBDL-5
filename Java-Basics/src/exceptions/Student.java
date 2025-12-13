package exceptions;

import java.sql.SQLException;

public class Student {

    public void findStudentByRollNo(int rollNo) throws EvenException, ClassNotFoundException {


//        try {


            if (rollNo < 50) {
                throw new ClassNotFoundException();
            } else if (rollNo % 2 == 0) {
                throw new EvenException();
            } else if (rollNo % 2 == 1) {
                throw new OddException();
            }
//        }catch (ClassNotFoundException cnfe){
//            System.out.println("Roll Number is less than 50");
//        }
//        catch (OddException oe){
//            System.out.println("Odd Exception");
//        }catch (EvenException ee){
//            System.out.println("Even Exception");
//        }catch (Exception e){
//            System.out.println("Exception");
//        }



    }
}
