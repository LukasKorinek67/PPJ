package com.korinek.MeteorologicalDataApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadConfiguration {

    @Bean
    ThreadPoolTaskScheduler weatherThreadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
