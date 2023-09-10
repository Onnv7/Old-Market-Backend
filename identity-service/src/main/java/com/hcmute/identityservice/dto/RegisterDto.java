package com.hcmute.identityservice.dto;

import com.hcmute.identityservice.model.UserModel;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    @NotBlank
    private String firstName;

    @NotBlank
    @NotNull(message = "Ko dc null")
    private String lastName;

    private UserModel.Gender gender;

    @Size(min = 6, max = 32)
    private String password;

    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;

    private String address = "";
    private UserModel.Role role;
}
