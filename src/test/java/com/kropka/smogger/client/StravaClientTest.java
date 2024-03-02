package com.kropka.smogger.client;

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
        String code = "e00af7a1e163a7c1b53db5ca7aa5e42ab0dc3463";
        //When
        Token token = stravaClient.getToken(code);
        //Then
        Assertions.assertNotNull(token);
    }
}
