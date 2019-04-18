package com.bcarnevale.minesweeperweb;

import MinePlacer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Random random() {
        return new RealRandomNumberGenerator();
    }

}
