package com.Irctc.Irctc_service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        ApiError apiError = new ApiError(LocalDateTime.now(),404,"Resource Not Found",ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<Map<String,String>> handleMethodArgumentException(MethodArgumentNotValidException e)
     {
         Map<String,String> map = new HashMap<>();

          e.getBindingResult().getFieldErrors().forEach(
                 ex->{
                     map.put(ex.getField(),ex.getDefaultMessage());
                 }
         );
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
     }

    @ExceptionHandler(InsufficientSeatsException.class)
     public ResponseEntity<ApiError> handleInsufficientSeats(InsufficientSeatsException ex)
     {
         ApiError e=new ApiError(LocalDateTime.now(),400,"Insufficient Seats",ex.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
     }

}
