package com.korinek.MeteorologicalDataApp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.io.File;

@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraProperties {
    private String secureConnectBundle;

    public String getSecureConnectBundle() {
        return secureConnectBundle;
    }

    public void setSecureConnectBundle(String secureConnectBundle) {
        this.secureConnectBundle = secureConnectBundle;
    }
}
