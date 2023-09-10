package com.hcmute.identityservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "user")
public class UserModel {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String birthDate;
    private String password;
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;
    private String address = "";
    private String introduction = "";
    private Role role = Role.ROLE_USER;
    private int star = 0;
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

