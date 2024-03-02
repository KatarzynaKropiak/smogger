package com.kropka.smogger.manager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class TokenManager {
    private final Map<String, String> tokens;

    private TokenManager() {
        this.tokens = new HashMap<String, String>();
    }

    public String retrieveToken(final String id) {
        final String token = this.tokens.get(id);
        return token;
    }

    public void storeToken(final String id, final String token) {
        this.tokens.put(id, token);
    }
}
