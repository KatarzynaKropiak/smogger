package com.kropka.smogger.client;

import com.kropka.smogger.config.StravaConfiguration;
import com.kropka.smogger.domain.StravaActivity;
import com.kropka.smogger.manager.TokenManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Mockito.when(stravaConfiguration.getActivitiesEndpoint()).thenReturn("https://test.com");

        StravaActivity[] mockActivities = {new StravaActivity(), new StravaActivity()};
        ResponseEntity<StravaActivity[]> mockResponseEntity = new ResponseEntity<>(mockActivities, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.eq(StravaActivity[].class)))
                .thenReturn(mockResponseEntity);

        List<StravaActivity> activities = stravaClient.getActivities(123);

        Mockito.verify(tokenManager).retrieveToken(123);

        Mockito.verify(restTemplate).exchange(Mockito.any(URI.class), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.eq(StravaActivity[].class));

        Assertions.assertNotNull(activities);
        Assertions.assertEquals(2, activities.size());

        String usedToken = tokenManager.retrieveToken(47863038);
        Assertions.assertEquals(mockedToken, usedToken);
    }
}