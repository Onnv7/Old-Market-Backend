package com.hcmute.identityservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private final String accessToken;
}
