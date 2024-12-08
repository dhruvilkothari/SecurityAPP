package com.example.SecurityApp.SecurityApp.Service;

import com.example.SecurityApp.SecurityApp.Advices.ApiError;
import com.example.SecurityApp.SecurityApp.Advices.ApiResponse;
import com.example.SecurityApp.SecurityApp.Dto.UserDto;
import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import com.example.SecurityApp.SecurityApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public ResponseEntity<ApiResponse<?>> createUser(UserDto userDto) {
        try{
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
            userRepository.save(userEntity);
            ApiResponse<UserDto> apiResponse = new ApiResponse<>(userDto);
            return ResponseEntity.ok(apiResponse);

        }catch (Exception exp){
            ApiError apiError = new ApiError(exp.getMessage(),HttpStatus.BAD_REQUEST);
            ApiResponse<ApiError>apiResponse = new ApiResponse<>(apiError);
            return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ApiResponse<?>> loginUser(UserDto userDto) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDto.getEmail(),
                    userDto.getPassword()
            ));
            UserEntity userEntity = userRepository.findByEmail(userDto.getEmail())
                    .orElseThrow();

            String token = jwtService.getToken(userEntity);
            ApiResponse<String>apiResponse = new ApiResponse<>(token);

            return ResponseEntity.ok(apiResponse);
        }catch (Exception exp){
            ApiError apiError = new ApiError(exp.getMessage(),HttpStatus.FORBIDDEN);
            ApiResponse<ApiError>apiResponse = new ApiResponse<>(apiError);
            return new ResponseEntity<>(apiResponse,HttpStatus.FORBIDDEN);
        }


    }
}
