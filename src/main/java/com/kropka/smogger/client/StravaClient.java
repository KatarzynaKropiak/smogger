package com.kropka.smogger.client;

import com.kropka.smogger.config.StravaConfiguration;
import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StravaClient {
    private final StravaConfiguration stravaConfiguration;

    public Token getToken(String code) {
        AuthorisationService service = new AuthorisationServiceImpl();
        return service.tokenExchange(stravaConfiguration.getClientId(),
                stravaConfiguration.getSecretClient(), code);
    }

}