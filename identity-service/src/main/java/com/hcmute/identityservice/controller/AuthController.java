package com.hcmute.identityservice.controller;

import com.hcmute.identityservice.dto.LoginRequestDto;
import com.hcmute.identityservice.dto.LoginResponseDto;
import com.hcmute.identityservice.dto.RegisterDto;
import com.hcmute.identityservice.service.AuthService;
import com.hcmute.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Validated LoginRequestDto request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid  RegisterDto request) {
        try {
            return new ResponseEntity<>(userService.registerNewUser(request), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        try {
            System.out.println("=>>> validating");
            authService.validateToken(token);
            return "Validted";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/ab")
    public String ab(@RequestParam("token") String token) {
        try {
            return "av";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
