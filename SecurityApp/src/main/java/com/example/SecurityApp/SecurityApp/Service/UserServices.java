package com.example.SecurityApp.SecurityApp.Service;

import com.example.SecurityApp.SecurityApp.Advices.ApiError;
import com.example.SecurityApp.SecurityApp.Advices.ApiResponse;
import com.example.SecurityApp.SecurityApp.Dto.PostDto;
import com.example.SecurityApp.SecurityApp.Entity.PostEntity;
import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import com.example.SecurityApp.SecurityApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServices implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        if(op.isPresent()){
            return op.get();
        }
        return null;
    }

    public ResponseEntity<ApiResponse<?>> getAllPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication+"Hello World");

        if (authentication == null ) {
            ApiError apiError = new ApiError("Not Authenticated", HttpStatus.FORBIDDEN);
            ApiResponse<ApiError> apiResponse = new ApiResponse<>(apiError);
            return new ResponseEntity<>(apiResponse,HttpStatus.FORBIDDEN);
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity= userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()-> new BadCredentialsException("User Not Authenticated"));
        List<PostEntity> postEntityList = userEntity.getPosts();
         List<PostDto> postDtos = postEntityList.stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .toList();
         ApiResponse<List<PostDto>>p = new ApiResponse<>(postDtos);
         return ResponseEntity.ok(p);

    }
}
