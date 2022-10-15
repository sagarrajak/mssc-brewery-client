package com.springframework.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizerApacheHttp implements RestTemplateCustomizer {
    private final int maxTotalConnection;
    private final int defaultMaxPerRoute;
    private final int connectionTimeout;
    private final int socketTimeout;

    public BlockingRestTemplateCustomizerApacheHttp(@Value("${sfg.maxTotalConnection}") int maxTotalConnection,
                                                    @Value("${sfg.defaultMaxPerRoute}") int defaultMaxPerRoute,
                                                    @Value("${sfg.connectionTimeout}") int connectionTimeout,
                                                    @Value("${sfg.socketTimeout}") int socketTimeout) {
        this.maxTotalConnection = maxTotalConnection;
        this.defaultMaxPerRoute = defaultMaxPerRoute;
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnection);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
