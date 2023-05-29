package com.korinek.MeteorologicalDataApp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather.source")
public class DataSourceConfiguration {

    @Value("${weather.source.url}")
    private String url;
    @Value("${weather.source.url-geocoding}")
    private String urlGeocoding;
    @Value("${weather.source.api-key}")
    private String apiKey;
    @Value("${weather.source.update-rate}")
    private int updateRate;
    @Value("${weather.source.max-calls}")
    private int maxCalls;
    // max 60 calls per minute


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlGeocoding() {
        return urlGeocoding;
    }

    public void setUrlGeocoding(String urlGeocoding) {
        this.urlGeocoding = urlGeocoding;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getUpdateRate() {
        return updateRate;
    }

    public void setUpdateRate(int updateRate) {
        if(updateRate < 1) {
            return;
        }
        if((updateRate / 60) > this.maxCalls) {
            updateRate = 1;
        }
        this.updateRate = updateRate;
    }

    public int getMaxCalls() {
        return maxCalls;
    }

    public void setMaxCalls(int maxCalls) {
        if(maxCalls <= 0) {
            return;
        }
        this.maxCalls = maxCalls;
    }
}
