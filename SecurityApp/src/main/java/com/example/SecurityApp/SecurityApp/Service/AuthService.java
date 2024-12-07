package com.example.SecurityApp.SecurityApp.Service;

import com.example.SecurityApp.SecurityApp.Dto.UserDto;
import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import com.example.SecurityApp.SecurityApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        try{
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
            userRepository.save(userEntity);
            return ResponseEntity.ok(userDto);

        }catch (Exception exp){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
