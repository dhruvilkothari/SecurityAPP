package com.example.SecurityApp.SecurityApp.Service;

import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import com.example.SecurityApp.SecurityApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        if(op.isPresent()){
            return op.get();
        }
        return null;
    }
}
