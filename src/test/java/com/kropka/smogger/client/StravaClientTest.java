package com.kropka.smogger.client;

import com.kropka.smogger.config.StravaConfiguration;
import com.kropka.smogger.domain.Activity;
import com.kropka.smogger.domain.TokenResponse;
import com.kropka.smogger.manager.TokenManager;
import javastrava.api.v3.auth.model.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StravaClientTest {

    @InjectMocks
    private StravaClient stravaClient;

    @Mock
    private TokenManager tokenManager;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private StravaConfiguration stravaConfiguration;

    @Test
    public void testGetActivitiesWithSameToken() {
        String mockedToken = "mockedToken";
        Mockito.when(tokenManager.retrieveToken(Mockito.anyInt())).thenReturn(mockedToken);
        Mockito.when(stravaConfiguration.getActivitiesEnd()).thenReturn("https://test.com");

        Activity[] mockActivities = {new Activity(), new Activity()};
        ResponseEntity<Activity[]> mockResponseEntity = new ResponseEntity<>(mockActivities, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.eq(Activity[].class)))
                .thenReturn(mockResponseEntity);

        List<Activity> activities = stravaClient.getActivities(123);

        Mockito.verify(tokenManager).retrieveToken(123);

        Mockito.verify(restTemplate).exchange(Mockito.any(URI.class), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.eq(Activity[].class));

        Assertions.assertNotNull(activities);
        Assertions.assertEquals(2, activities.size());

        String usedToken = tokenManager.retrieveToken(123);
        Assertions.assertEquals(mockedToken, usedToken);
    }
}