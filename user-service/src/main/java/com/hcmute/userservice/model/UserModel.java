package com.hcmute.userservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "user")
public class UserModel {

    @MongoId
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private Gender gender;
    private String birthDate;

    @Size(min = 6)
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;
    private String address;
    private String introduction;

    @NotBlank
    private Role role;
    private int star;
    public enum Gender {
        FEMALE,
        MALE,
        OTHER,
    }
    public enum Role {
        ROLE_USER,
        ROLE_ADMIN,
    }
}

