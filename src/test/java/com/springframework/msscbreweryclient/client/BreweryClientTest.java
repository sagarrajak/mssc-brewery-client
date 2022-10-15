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
   void getBeerById() {
        BeerDto beerDto = this.breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }
}