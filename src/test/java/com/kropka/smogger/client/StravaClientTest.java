package com.kropka.smogger.client;

import com.kropka.smogger.domain.Activity;
import com.kropka.smogger.domain.TokenResponse;
import javastrava.api.v3.auth.model.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StravaClientTest {

    @Autowired
    private StravaClient stravaClient;

    @BeforeEach
    void shouldGetToken() {
        //Given
        String code = "fb9d9e89ec9ec4c192f293239aa1b1c00105c0e7";
        //When
        TokenResponse token = stravaClient.exchangeToken(code);
//        //Then
//        Assertions.assertNotNull(token);
    }

    @Test
    void shouldGetActivities() {
        //Given
        int id = 47863038;
        //When
        List<Activity> activities = stravaClient.getActivities(id);
        //Then
        Assertions.assertNotNull(activities);
    }
}
