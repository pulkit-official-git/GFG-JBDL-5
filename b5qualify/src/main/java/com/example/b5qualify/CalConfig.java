package com.example.b5qualify;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalConfig {

    @Bean("uk-bean")
    public UkCalculator getUkCalculator() {
        return new UkCalculator();
    }

    @Bean("us-bean")
    public UsCalculator getUsCalculator() {
        return new UsCalculator();
    }
}
