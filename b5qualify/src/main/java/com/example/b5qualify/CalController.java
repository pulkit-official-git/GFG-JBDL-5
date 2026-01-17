package com.example.b5qualify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CalController {

    @Autowired
    @Qualifier("us-bean")
    private Calculator calculator;

    @GetMapping("/calculator")
    public HashMap<String,Integer> calculate(@RequestParam Integer a, @RequestParam Integer b) {

        int sum = this.calculator.add(a,b);
        int diff = this.calculator.subtract(a,b);

        HashMap<String,Integer> map = new HashMap<>();
        map.put("sum",sum);
        map.put("diff",diff);
        return map;
    }
}
