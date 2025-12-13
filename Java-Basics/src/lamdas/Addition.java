package lamdas;

public class Addition implements MathematicalOperator{
    @Override
    public int operate(int a, int b) {
        return a+b;
    }
}
