package com.hcmute.identityservice.service;

import com.hcmute.identityservice.dto.LoginResponseDto;
import com.hcmute.identityservice.security.JwtUtils;
import com.hcmute.identityservice.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    public LoginResponseDto attemptLogin(String email, String password) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();
        var roles = principal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtUtils.issue(principal.getUserId(), principal.getEmail(), roles);
        return LoginResponseDto.builder().accessToken(token).build();
    }

    public void validateToken(String token) {
        jwtUtils.decode(token);
    }
}
