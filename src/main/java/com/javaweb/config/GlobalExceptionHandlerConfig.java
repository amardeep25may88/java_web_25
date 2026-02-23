package com.javaweb.config;


import com.javaweb.apperrors.BusinessException;
import com.javaweb.apperrors.ErrorResponse;
import com.javaweb.apperrors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig {

    // ===============================
    // BUSINESS EXCEPTION
    // ===============================
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex, WebRequest request) {

        return buildError(
                HttpStatus.BAD_REQUEST,
                ex.getErrorCode().getCode(),
                ex.getMessage(),
                request
        );
    }

    // ===============================
    // VALIDATION EXCEPTIONS
    // ===============================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildError(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                message,
                request
        );
    }

    // ===============================
    // HTTP / WEB EXCEPTIONS
    // ===============================

    // 400 - Missing request parameter
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(
            MissingServletRequestParameterException ex, WebRequest request) {

        return buildError(
                HttpStatus.BAD_REQUEST,
                "MISSING_REQUEST_PARAM",
                ex.getParameterName() + " parameter is missing",
                request
        );
    }

    // 400 - Invalid JSON / request body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(
            HttpMessageNotReadableException ex, WebRequest request) {

        return buildError(
                HttpStatus.BAD_REQUEST,
                "INVALID_REQUEST_BODY",
                "Malformed JSON request",
                request
        );
    }

    // 405 - HTTP method not supported
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, WebRequest request) {

        return buildError(
                HttpStatus.METHOD_NOT_ALLOWED,
                "METHOD_NOT_ALLOWED",
                ex.getMessage(),
                request
        );
    }

    // 404 - No handler found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFound(
            NoHandlerFoundException ex, WebRequest request) {

        return buildError(
                HttpStatus.NOT_FOUND,
                "ENDPOINT_NOT_FOUND",
                "No endpoint found for this URL",
                request
        );
    }

    // ===============================
    // RESOURCE NOT FOUND (BUSINESS)
    // ===============================
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {

        return buildError(
                HttpStatus.NOT_FOUND,
                "RESOURCE_NOT_FOUND",
                ex.getMessage(),
                request
        );
    }

    // ===============================
    // FALLBACK / SYSTEM EXCEPTION
    // ===============================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {

        return buildError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "Unexpected error occurred",
                request
        );
    }

    // ===============================
    // COMMON ERROR BUILDER
    // ===============================
    private ResponseEntity<ErrorResponse> buildError(
            HttpStatus status,
            String errorCode,
            String message,
            WebRequest request) {

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .errorCode(errorCode)
                .message(message)
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(error, status);
    }
}