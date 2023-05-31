package com.korinek.MeteorologicalDataApp;

import com.korinek.MeteorologicalDataApp.configuration.DataSourceConfiguration;
import com.korinek.MeteorologicalDataApp.configuration.DataStaxAstraProperties;
import com.korinek.MeteorologicalDataApp.configuration.ExpirationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@SpringBootApplication
@EnableConfigurationProperties({DataStaxAstraProperties.class, DataSourceConfiguration.class, ExpirationConfiguration.class})
public class MeteorologicalDataAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteorologicalDataAppApplication.class, args);
		System.out.println("APP RUNNING ON PORT 8080");
	}


	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		String bundlePath = astraProperties.getSecureConnectBundle();
		Resource resource = new ClassPathResource(bundlePath);
		try (InputStream inputStream = resource.getInputStream()) {
			File tempFile = File.createTempFile("secure-connect", ".zip");
			Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			return builder -> builder.withCloudSecureConnectBundle(tempFile.toPath());
		} catch (IOException e) {
			throw new RuntimeException("Failed to load secure connect bundle", e);
		}
	}
}
