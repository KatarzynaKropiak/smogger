package com.kropka.smogger.client;

import com.kropka.smogger.config.StravaConfiguration;
import com.kropka.smogger.manager.TokenManager;
import com.kropka.smogger.domain.TokenResponse;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

        return restTemplate.postForObject(url, null, TokenResponse.class);

    }

    // List<Activity> getActivities(String athleteId) {
    // token = tokenManager.retrieveToken(athleteId)
    // result = http GET "https://www.strava.com/api/v3/athlete/activities?before=&after=&page=&per_page=" "Authorization: Bearer [[token]]"
    // return result
    // }

}