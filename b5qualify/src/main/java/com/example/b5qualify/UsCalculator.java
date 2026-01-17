package com.example.b5qualify;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("us-bean")
//@Primary
public class UsCalculator implements Calculator {

    @Override
    public int add(int a, int b) {
        return a+b+1;
    }

    @Override
    public int subtract(int a, int b) {
        return a-b;
    }
}
