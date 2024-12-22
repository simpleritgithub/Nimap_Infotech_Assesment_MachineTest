package com.product.management.product.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ApiResponse {
    private String message = "Something Went Wrong!";
    private LocalDateTime timeStamp;

    public ApiResponse() {
        // TODO Auto-generated constructor stub
    }

    public ApiResponse(String message) {
        super();
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
