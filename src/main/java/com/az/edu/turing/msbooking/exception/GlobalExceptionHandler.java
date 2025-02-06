package com.az.edu.turing.msbooking.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

import static com.az.edu.turing.msbooking.model.constants.ErrorCode.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<GlobalErrorResponse> alreadyExistsExceptionHandler(AlreadyExistsException exception) {
        exceptionLog(exception);
        return ResponseEntity.status(BAD_REQUEST)
                .body(GlobalErrorResponse.builder()
                        .requestId(UUID.randomUUID())
                        .errorCode(USER_ALREADY_EXISTS)
                        .errorMessage(exception.getMessage())
                        .localDateTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> notFoundExceptionHandler(NotFoundException exception) {
        exceptionLog(exception);
        return ResponseEntity.status(NOT_FOUND)
                .body(GlobalErrorResponse.builder()
                        .requestId(UUID.randomUUID())
                        .errorCode(USER_NOT_FOUND)
                        .errorMessage(exception.getMessage())
                        .localDateTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<GlobalErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        exceptionLog(ex);
        return ResponseEntity.status(UNAUTHORIZED)
                .body(GlobalErrorResponse.builder()
                        .requestId(UUID.randomUUID())
                        .errorCode(INVALID_USER_REQUEST)
                        .errorMessage(ex.getMessage())
                        .localDateTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
        exceptionLog(ex);
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Null pointer exception occurred");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void exceptionLog(Exception exception) {
        log.info("{} happened {}", exception.getClass().getSimpleName(), exception.getMessage());
    }
}
