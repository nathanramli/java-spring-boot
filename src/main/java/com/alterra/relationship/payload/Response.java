package com.alterra.relationship.payload;

import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@SuperBuilder
public class Response extends BaseResponse {
    public static ResponseEntity<Object> build(String message, HttpStatus httpStatus, Object data) {
        return new ResponseEntity<>(Response
                .builder()
                .responseCode(Integer.toString(httpStatus.value()))
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build(), httpStatus);
    }
}
