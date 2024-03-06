package com.kropka.smogger.manager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class TokenManager {
    private final Map<Integer, String> tokens;

    private TokenManager() {
        this.tokens = new HashMap<Integer, String>();
    }

    public String retrieveToken(final int id) {
        final String token = this.tokens.get(id);
        return token;
    }

    public void storeToken(final int id, final String token) {
        this.tokens.put(id, token);
    }
}
