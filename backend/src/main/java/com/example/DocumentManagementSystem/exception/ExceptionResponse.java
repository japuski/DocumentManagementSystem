package com.example.DocumentManagementSystem.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class ExceptionResponse {

    String errorTime;
    String errorMessage;

    static ExceptionResponse getException(RuntimeException runtimeException){
        return ExceptionResponse.builder()
                .errorMessage(runtimeException.getMessage())
                .errorTime(String.valueOf(LocalDateTime.now()))
                .build();
    }

}
