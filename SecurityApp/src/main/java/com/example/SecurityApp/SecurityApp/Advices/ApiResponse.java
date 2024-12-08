package com.example.SecurityApp.SecurityApp.Advices;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private ApiError apiError;
    private LocalDateTime timestamp;

    public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(T data){
        this();
        this.data = data;
    }
    public ApiResponse(ApiError apiError){
        this();
        this.apiError = apiError;
    }

}
