package com.hcmute.userservice.dto;

import com.hcmute.userservice.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    private String name;
    private UserModel.Gender gender;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
}
