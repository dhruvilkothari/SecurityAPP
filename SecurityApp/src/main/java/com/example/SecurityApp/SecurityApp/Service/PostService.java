package com.example.SecurityApp.SecurityApp.Service;

import com.example.SecurityApp.SecurityApp.Advices.ApiError;
import com.example.SecurityApp.SecurityApp.Advices.ApiResponse;
import com.example.SecurityApp.SecurityApp.Dto.PostDto;
import com.example.SecurityApp.SecurityApp.Entity.PostEntity;
import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import com.example.SecurityApp.SecurityApp.Repository.PostRepository;
import com.example.SecurityApp.SecurityApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<ApiResponse<?>> createPost(PostDto postDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication+"Hello World");

        if (authentication == null ) {
            ApiError apiError = new ApiError("Not Authenticated", HttpStatus.FORBIDDEN);
            ApiResponse<ApiError> apiResponse = new ApiResponse<>(apiError);
            return new ResponseEntity<>(apiResponse,HttpStatus.FORBIDDEN);
        }
        try{
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            UserEntity userEntity= userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()-> new BadCredentialsException("User Not Authenticated"));
            PostEntity postEntity=modelMapper.map(postDto, PostEntity.class);
            postEntity.setUser(userEntity);
            postRepository.save(postEntity);
            List<PostEntity>p = userEntity.getPosts();
            p.add(postEntity);
            userEntity.setPosts(p);
            userRepository.save(userEntity);

            ApiResponse<PostEntity> postEntityApiResponse = new ApiResponse<>(postEntity);
            return ResponseEntity.ok(postEntityApiResponse);


        }catch (Exception exp){

            System.out.println(exp.getMessage());
        }


        return null;

    }
}
