package com.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokens {
    private String accessToken;
    private String refreshToken;

    public static AuthTokens of(String accessToken, String refreshToken) {
        return new AuthTokens(accessToken, refreshToken);
    }
}
