package com.korinek.MeteorologicalDataApp.configuration.ReadOnly;

import com.korinek.MeteorologicalDataApp.MeteorologicalDataAppApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Conditional(ReadOnly.class)
@Configuration

public class ReadOnlyConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(MeteorologicalDataAppApplication.class);

    public ReadOnlyConfiguration() {
        log.info("Read only mode is ON");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReadOnlyInterceptor());
    }

}
