package com.kropka.smogger.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GiosConfiguration {

    @Value("${gios.api.endpoint.aq}")
    private String aqendpoint;

    @Value("${gios.api.endpoint.findAll}")
    private String allStations;
}
