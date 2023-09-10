package com.hcmute.identityservice.security;

import com.hcmute.identityservice.model.UserModel;
import com.hcmute.identityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().equals(UserModel.Role.ROLE_USER) ? "ROLE_USER" : "ROLE_ADMIN")))
                .password(user.getPassword())
                .build();
    }
}
