package com.example.jobhunter.ExceptionError;

public class ScraperException extends RuntimeException{
    public ScraperException(String message, Throwable cause){
        super(message, cause);
    }
}
