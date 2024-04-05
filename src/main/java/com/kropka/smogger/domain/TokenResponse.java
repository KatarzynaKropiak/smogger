package com.kropka.smogger.domain;

import lombok.Data;

@Data
public class TokenResponse {

    String token_type;
    String refresh_token;
    String access_token;
    StravaAthlete athlete;
}
