package com.example.DocumentManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handlerValidation(MethodArgumentNotValidException methodArgumentNotValidException){

        List<ObjectError> objectErrorList = methodArgumentNotValidException.getBindingResult().getAllErrors();
        List<String> errorMessages = objectErrorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages);
    }

    @ExceptionHandler(InvoiceTemplateNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleInvoceTemplate(InvoiceTemplateNotFoundException invoiceTemplateNotFoundException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionResponse.getException(invoiceTemplateNotFoundException));

    }

}
