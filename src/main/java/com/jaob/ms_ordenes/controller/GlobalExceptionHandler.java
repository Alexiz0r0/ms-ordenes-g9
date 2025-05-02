package com.jaob.ms_ordenes.controller;

import com.jaob.ms_ordenes.aggregates.constants.Constantes;
import com.jaob.ms_ordenes.aggregates.response.ResponseBase;
import com.jaob.ms_ordenes.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseBase<String>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ResponseBase<String> response = new ResponseBase<>(
                Constantes.CODE_NOT_FOUND,
                true,
                exception.getMessage(),
                null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
