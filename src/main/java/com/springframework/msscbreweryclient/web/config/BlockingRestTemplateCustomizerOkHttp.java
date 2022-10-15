package com.springframework.msscbreweryclient.web.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;
public class BlockingRestTemplateCustomizerOkHttp implements RestTemplateCustomizer {
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        ConnectionPool connectionPool = new ConnectionPool(100, 300, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient.Builder().connectionPool(connectionPool).connectTimeout(3000, TimeUnit.MILLISECONDS).build();
        return new OkHttp3ClientHttpRequestFactory(client);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
