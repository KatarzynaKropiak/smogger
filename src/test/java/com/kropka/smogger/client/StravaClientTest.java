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
        String code = "b819c934d09823a08985f2c4d47a150ebddc9dd7";
        //When
        TokenResponse token = stravaClient.exchangeToken(code);
        //Then
        Assertions.assertNotNull(token);
    }

    // shouldGetActivities {
    //  stravaClient.getActivities(athleteId)
    // }
}
