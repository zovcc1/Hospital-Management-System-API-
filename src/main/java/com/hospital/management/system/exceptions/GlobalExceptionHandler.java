package com.hospital.management.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND , ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
}
@ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handelAlreadyExistsException(AlreadyExistsException ex){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT , ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
}




}
