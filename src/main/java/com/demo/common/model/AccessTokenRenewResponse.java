package com.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessTokenRenewResponse {
    private String accessToken;
    private Boolean isLogout;
}
