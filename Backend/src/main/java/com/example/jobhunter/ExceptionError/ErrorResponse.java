package com.example.jobhunter.ExceptionError;

import java.time.LocalDateTime;

public class ErrorResponse {
    private Integer status;
    private String path;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(Integer status, String path, String message) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }


    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
