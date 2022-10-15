package com.springframework.msscbreweryclient.web.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;
    public  BeerDto(BeerDto beerDto) {
        this.beerName = beerDto.getBeerName();
        this.beerStyle = beerDto.getBeerStyle();
        this.upc = beerDto.getUpc();
        this.id = UUID.randomUUID();
    }
}


