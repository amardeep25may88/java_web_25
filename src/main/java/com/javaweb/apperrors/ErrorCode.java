package com.javaweb.apperrors;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // ===============================
    // BUSINESS ERRORS
    // ===============================
    BUSINESS_ERROR("BUS_001", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("BUS_002", HttpStatus.NOT_FOUND),

    // ===============================
    // VALIDATION ERRORS
    // ===============================
    VALIDATION_ERROR("VAL_001", HttpStatus.BAD_REQUEST),
    MISSING_REQUEST_PARAM("VAL_002", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_BODY("VAL_003", HttpStatus.BAD_REQUEST),

    // ===============================
    // HTTP / WEB ERRORS
    // ===============================
    ENDPOINT_NOT_FOUND("HTTP_404", HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED("HTTP_405", HttpStatus.METHOD_NOT_ALLOWED),

    // ===============================
    // SECURITY ERRORS (OPTIONAL)
    // ===============================
    UNAUTHORIZED("SEC_401", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("SEC_403", HttpStatus.FORBIDDEN),

    // ===============================
    // SYSTEM ERRORS
    // ===============================
    INTERNAL_SERVER_ERROR("SYS_500", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final HttpStatus httpStatus;

    ErrorCode(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
