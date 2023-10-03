package com.example.fingryd.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found!");
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerValue(NullPointerException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The value you enter was incorrect. Check data...");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("General error! We are looking into it Now...");
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> internalServerError(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server Error, Please be patient..");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> resourceNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with the details cannot be found");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodValidationException(MethodArgumentNotValidException e){
        Map<String, String> error = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError-> error.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
