package com.example.jobhunter.ExceptionError;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //Exepciones del scraper
    @ExceptionHandler(ScraperException.class)
    public ResponseEntity<ErrorResponse> handleScraperException(ScraperException e, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_GATEWAY.value(), request.getRequestURI(),e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(response);

    }
    //Exepciones globales
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException( RuntimeException e,HttpServletRequest request) {
        logger.error("Error inesperado al procesar la solicitud", e);
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI(),"Ocurrió un error interno en el servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    //Exepciones de los jobs
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobException(JobNotFoundException e,HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    //Exepciones @NotBlank
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), errors.stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(", ")));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);

    }
}
