package com.javaweb.apperrors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    // Time when error occurred
    private LocalDateTime timestamp;

    // HTTP status code (e.g. 400, 404, 500)
    private int status;

    // Business / system error code (e.g. VAL_001, BUS_002)
    private String errorCode;

    // Human-readable error message
    private String message;

    // API path that caused the error
    private String path;

    // Optional: request correlation / trace id (for logs)
    private String traceId;

    // Optional: detailed validation errors
    private Object details;
}
