package com.example.sight.global.security.usecase;

import com.example.sight.domain.user.domain.User;
import com.example.sight.domain.user.domain.repo.UserRepo;
import com.example.sight.domain.user.error.UserErrorCode;
import com.example.sight.global.exception.CustomException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepo.findByName(name)
                .map(this::createUserDetails)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
    }

    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority =
                new SimpleGrantedAuthority(user.getAuthority().toString());

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getId()),   // username = userId
                user.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}