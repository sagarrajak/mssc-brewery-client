package com.springframework.msscbreweryclient.client;

import com.springframework.msscbreweryclient.web.client.BreweryClient;
import com.springframework.msscbreweryclient.web.models.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void testGetApi() {
        BeerDto beerDto = this.breweryClient.getBeerById(UUID.fromString("b0e011ff-1baf-4791-aa85-39111a7ce65a"));
        assertNotNull(beerDto);
    }
}