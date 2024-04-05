package com.kropka.smogger.client;

import com.kropka.smogger.config.StravaConfiguration;
import com.kropka.smogger.domain.StravaActivity;
import com.kropka.smogger.manager.TokenManager;
import com.kropka.smogger.domain.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StravaClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(StravaClient.class);
    private final static String GRANT_TYPE = "authorization_code";

    private final StravaConfiguration stravaConfiguration;
    private final TokenManager tokenManager;
    private final RestTemplate restTemplate;

    public TokenResponse exchangeCodeForToken(String code) {

        URI url = UriComponentsBuilder.fromHttpUrl(stravaConfiguration.getOAuthTokenEndpoint())
                .queryParam("client_id", stravaConfiguration.getClientId())
                .queryParam("client_secret", stravaConfiguration.getSecretClient())
                .queryParam("code", code)
                .queryParam("grant_type", GRANT_TYPE)
                .build()
                .encode()
                .toUri();

        TokenResponse response = restTemplate.postForObject(url, null, TokenResponse.class);
        tokenManager.storeToken(response.getAthlete().getId(), response.getAccess_token());
        LOGGER.info("THIS IS ATHLETE ID: " + response.getAthlete().getId());
        LOGGER.info("THIS IS TOKEN: " + response.getAccess_token());
        return response;
    }

     public List<StravaActivity> getActivities(int athleteId) {
     String token = tokenManager.retrieveToken(athleteId);
         LOGGER.info("THIS IS THE SAME TOKEN: " + token);
         URI url = UriComponentsBuilder.fromHttpUrl(stravaConfiguration.getActivitiesEndpoint())
                 .build()
                 .encode()
                 .toUri();

         HttpHeaders headers = new HttpHeaders();
         headers.set("Authorization", "Bearer " + token);

         HttpEntity<?> httpEntity = new HttpEntity<>(headers);

         ResponseEntity<StravaActivity[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, StravaActivity[].class);
         if (response.getStatusCode() == HttpStatus.OK) {
             StravaActivity[] activities = response.getBody();
             return Arrays.asList(activities);
         } else {
             return Collections.emptyList();
         }
     }

}