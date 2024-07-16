package com.fetch.rewards.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestBodyValidator {
    private final ObjectMapper objectMapper;

    public RequestBodyValidator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean isValidJson(String json, Class<?> targetClass) {
        try {
            objectMapper.readValue(json, targetClass);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
