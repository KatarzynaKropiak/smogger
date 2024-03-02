package com.kropka.smogger.client;

import com.kropka.smogger.domain.TokenResponse;
import javastrava.api.v3.auth.model.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StravaClientTest {

    @Autowired
    private StravaClient stravaClient;

    @Test
    void shouldGetToken() {
        //Given
        String code = "75509552c00ca7f2d1fce05325ff7b0024723056";
        //When
        TokenResponse token = stravaClient.exchangeToken(code);
        //Then
        Assertions.assertNotNull(token);
    }

}
