package com.hcmute.userservice.controller;

import com.hcmute.userservice.dto.UserInfoRequest;
import com.hcmute.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@RequestBody UserInfoRequest body) {
        try {
            System.out.println("asdasdasd");
            userService.createNewUser(body);
            return new ResponseEntity<> ("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/ok")
    public String createNewUser1() {
        try {
            return "sadsd";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
