package com.kropka.smogger.client;

import com.kropka.smogger.config.StravaConfiguration;
import com.kropka.smogger.domain.Activity;
import com.kropka.smogger.manager.TokenManager;
import com.kropka.smogger.domain.TokenResponse;
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
    private final StravaConfiguration stravaConfiguration;
    private final TokenManager tokenManager;
    private final RestTemplate restTemplate;
    private final static String GRANT_TYPE = "authorization_code";

    public TokenResponse exchangeToken(String code) {

        URI url = UriComponentsBuilder.fromHttpUrl(stravaConfiguration.getOAuthTokenEnd())
                .queryParam("client_id", stravaConfiguration.getClientId())
                .queryParam("client_secret", stravaConfiguration.getSecretClient())
                .queryParam("code", code)
                .queryParam("grant_type", GRANT_TYPE)
                .build()
                .encode()
                .toUri();

        TokenResponse response = restTemplate.postForObject(url, null, TokenResponse.class);
        tokenManager.storeToken(response.getAthlete().getId(), response.getAccess_token());
        System.out.println("TO JEST TOKEN: " + response.getAccess_token());
        return response;
    }

     public List<Activity> getActivities(int athleteId) {
     String token = tokenManager.retrieveToken(athleteId);
     System.out.println("TO JEST TEN SAM TOKEN: " + token);
         URI url = UriComponentsBuilder.fromHttpUrl(stravaConfiguration.getActivitiesEnd())
                 .build()
                 .encode()
                 .toUri();

         HttpHeaders headers = new HttpHeaders();
         headers.set("Authorization", "Bearer " + token);

         HttpEntity<?> httpEntity = new HttpEntity<>(headers);

         ResponseEntity<Activity[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Activity[].class);
         if (response.getStatusCode() == HttpStatus.OK) {
             Activity[] activities = response.getBody();
             return Arrays.asList(activities);
         } else {
             return Collections.emptyList();
         }
     }

}