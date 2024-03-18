package com.kropka.smogger.controller;

import com.kropka.smogger.client.StravaClient;
import com.kropka.smogger.domain.Activity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Component
@RequiredArgsConstructor
public class StravaController {

    private final StravaClient stravaClient;

    @GetMapping("/login/oauth2/code/strava")
    public void authorizeStrava(@RequestParam(value = "code") String code,
                                @RequestParam(value = "scope") String scope,
                                HttpServletResponse response) throws IOException {
        stravaClient.exchangeToken(code);
        response.sendRedirect("/activities");
    }

    @GetMapping("/activities")
    public List<Activity> getActivities() {
        List<Activity> activities;
        activities = stravaClient.getActivities(47863038);
        return activities;
    }
}