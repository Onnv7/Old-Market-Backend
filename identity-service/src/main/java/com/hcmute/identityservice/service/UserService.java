package com.hcmute.identityservice.service;

import com.hcmute.identityservice.dto.RegisterDto;
import com.hcmute.identityservice.model.UserModel;
import com.hcmute.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Optional<UserModel> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public String registerNewUser(RegisterDto newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(UserModel.Role.ROLE_USER);
        UserModel a = new UserModel();
        modelMapper.map(newUser, a);
        UserModel user = modelMapper.map(newUser, UserModel.class);
        user.setId(UUID.randomUUID().toString());
        if(user != null) {
            userRepository.save(user);
            return "Register successfully";
        }
        return "Register failed";
    }
}
