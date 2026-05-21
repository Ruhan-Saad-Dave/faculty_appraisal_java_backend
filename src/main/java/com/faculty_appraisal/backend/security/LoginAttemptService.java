package com.faculty_appraisal.backend.security;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPT = 5;
    private static final int BLOCK_DURATION_MINUTES = 1;

    private final Cache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        attemptsCache = Caffeine.newBuilder()
                .expireAfterWrite(BLOCK_DURATION_MINUTES, TimeUnit.MINUTES)
                .build();
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;
        Integer currentAttempts = attemptsCache.getIfPresent(key);
        if (currentAttempts != null) {
            attempts = currentAttempts;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        Integer attempts = attemptsCache.getIfPresent(key);
        return attempts != null && attempts >= MAX_ATTEMPT;
    }
}
