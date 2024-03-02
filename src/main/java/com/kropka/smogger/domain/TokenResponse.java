package com.kropka.smogger.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class TokenResponse {

    String token_type;
    String refresh_token;
    String access_token;
    Athlete athlete;
}
