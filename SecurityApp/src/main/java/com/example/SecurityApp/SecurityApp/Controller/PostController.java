package com.example.SecurityApp.SecurityApp.Controller;

import com.example.SecurityApp.SecurityApp.Advices.ApiResponse;
import com.example.SecurityApp.SecurityApp.Dto.PostDto;
import com.example.SecurityApp.SecurityApp.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity<ApiResponse<?>> createPost(@RequestBody(required = true)PostDto postDto){
        return postService.createPost(postDto);
    }
}
