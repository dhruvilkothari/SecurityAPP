package com.example.SecurityApp.SecurityApp.Advices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class ApiError {
    private String message;
    private HttpStatus status;

    public ApiError(String message,HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
