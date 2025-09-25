package com.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {
    private String accessToken;
    private String refreshToken;
    private Boolean isRegistered;
}
