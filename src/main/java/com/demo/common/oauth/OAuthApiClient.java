package com.demo.common.oauth;

import com.demo.common.oauth.kakao.KakaoTokenResponse;

public interface OAuthApiClient {

    OAuthProvider oAuthProvider();
    KakaoTokenResponse requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
