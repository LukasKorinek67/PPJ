package com.korinek.MeteorologicalDataApp;

import com.korinek.MeteorologicalDataApp.configuration.DataSourceConfiguration;
import com.korinek.MeteorologicalDataApp.configuration.DataStaxAstraProperties;
import com.korinek.MeteorologicalDataApp.configuration.ExpirationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;


@SpringBootApplication
@RestController
@EnableConfigurationProperties({DataStaxAstraProperties.class, DataSourceConfiguration.class, ExpirationConfiguration.class})
public class MeteorologicalDataAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteorologicalDataAppApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Meteorological Data App!";
	}


	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}
}
