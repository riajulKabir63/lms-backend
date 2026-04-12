package com.lmsf.org.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiError {
    private Map<String, Object> message =  new HashMap<>();

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.message.put("status", status);
        this.message.put("debugMessage", ex.getMessage());
        this.message.put("message", message);
    }
}
