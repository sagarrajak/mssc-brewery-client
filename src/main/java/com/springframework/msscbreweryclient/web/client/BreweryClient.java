package com.springframework.msscbreweryclient.web.client;

import com.springframework.msscbreweryclient.web.models.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BreweryClient {
    public final String beerPathV1 = "/api/v1/beer/";
    private String apihost;

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apihost+beerPathV1+uuid.toString(), BeerDto.class);
    }

    public BeerDto saveBeerDto(BeerDto beerDto) {
        return restTemplate.postForObject(apihost+beerPathV1, beerDto, BeerDto.class);
    }
}
