package com.kropka.smogger.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class StravaConfiguration {

    @Value("${strava.api.endpoint.oauth.token}")
    private String oAuthTokenEnd;
    @Value("${strava.api.endpoint.act}")
    private String activityEnd;
    @Value("${strava.client_id}")
    private int clientId;
    @Value("${strava.client_secret}")
    private String secretClient;
    @Value("${strava.grant_type}")
    private String grantType;
    @Value("${strava.access_token}")
    private String accessToken;
}

