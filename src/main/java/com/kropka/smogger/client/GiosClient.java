package com.kropka.smogger.client;

import com.kropka.smogger.config.GiosConfiguration;
import com.kropka.smogger.domain.AQIndex;
import com.kropka.smogger.domain.GiosStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GiosClient {

    private final RestTemplate restTemplate;
    private final GiosConfiguration giosConfiguration;



    public AQIndex getAQ(int stationId) {
        URI url = UriComponentsBuilder.fromHttpUrl(giosConfiguration.getAqendpoint())
                .path(String.valueOf(stationId))
                .build()
                .encode()
                .toUri();

        AQIndex response = restTemplate.getForObject(url, AQIndex.class);
        return response;
    }

    public List<GiosStation> getAllStations() {
        URI url = UriComponentsBuilder.fromHttpUrl(giosConfiguration.getAllStations())
                .build()
                .encode()
                .toUri();

        List<GiosStation> response;
        response = Arrays.stream(restTemplate.getForObject(url, GiosStation[].class)).toList();
        return response;
        }
    }

