package com.korinek.MeteorologicalDataApp.database;

import com.korinek.MeteorologicalDataApp.configuration.DataStaxAstraProperties;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CassandraSessionConfig {
    /**
     * This is necessary to have the Spring Boot app use the Astra secure bundle
     * to connect to the database
     */
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
