package com.example.b5qualify;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("uk-bean")
//@Primary
public class UkCalculator implements Calculator {

    @Override
    public int add(int a, int b) {
        return a+b;
    }
    @Override
    public int subtract(int a, int b) {
        return Math.abs(a-b);
    }

    public int multiply(int a, int b) {
        return a*b;
    }
}
