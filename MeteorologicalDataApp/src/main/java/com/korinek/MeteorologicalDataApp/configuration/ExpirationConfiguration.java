package com.korinek.MeteorologicalDataApp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather.data")
public class ExpirationConfiguration {
    @Value("${weather.data.expiration}")
    private long expiration;

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
