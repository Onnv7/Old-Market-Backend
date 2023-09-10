package com.hcmute.userservice.service;

import com.hcmute.userservice.dto.UserInfoRequest;
import com.hcmute.userservice.model.UserModel;
import com.hcmute.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void createNewUser(UserInfoRequest userInfoRequest) {
        UserModel user = modelMapper.map(userInfoRequest, UserModel.class);
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
    }
}
