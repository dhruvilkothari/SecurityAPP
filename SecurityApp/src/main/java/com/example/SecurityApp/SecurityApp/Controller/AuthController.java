package com.example.SecurityApp.SecurityApp.Controller;

import com.example.SecurityApp.SecurityApp.Advices.ApiResponse;
import com.example.SecurityApp.SecurityApp.Dto.UserDto;
import com.example.SecurityApp.SecurityApp.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody(required = true) UserDto userDto){
        return authService.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>>loginUser(@RequestBody(required = true) UserDto userDto){
        return authService.loginUser(userDto);
    }
}
