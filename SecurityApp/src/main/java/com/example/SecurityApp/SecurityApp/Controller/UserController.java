package com.example.SecurityApp.SecurityApp.Controller;

import com.example.SecurityApp.SecurityApp.Advices.ApiResponse;
import com.example.SecurityApp.SecurityApp.Service.UserServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userServices;

    private final ModelMapper modelMapper;
    @GetMapping("")
    public String test(){
        return "Testing";
    }

    @GetMapping("/getallposts")
    public ResponseEntity<ApiResponse<?>> getAllPosts(){
        return userServices.getAllPosts();
    }




}
