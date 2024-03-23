package com.kropka.smogger.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GiosConfiguration {

    @Value("${gios.api.endpoint.stationId}")
    private String stationEndPoint;
}
