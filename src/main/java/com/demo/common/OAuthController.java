package com.demo.common;

import com.demo.common.model.AccessTokenRenewResponse;
import com.demo.common.model.AuthLoginResponse;
import com.demo.common.oauth.kakao.KakaoLoginParams;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

//    @PostMapping("/kakao")
//    public ResponseEntity<AuthLoginResponse> loginKakao(@RequestBody KakaoLoginParams params) {
//        log.info("kakao code: {}", params);
//        return ResponseEntity.ok(oAuthLoginService.login(params));
//    }
@PostMapping("/kakao")
public ResponseEntity<Void> loginKakao(@RequestBody KakaoLoginParams params,
                                       HttpServletResponse response) {
    AuthLoginResponse loginResponse = oAuthLoginService.login(params);

    Cookie accessCookie = new Cookie("accessToken", loginResponse.getAccessToken());
    accessCookie.setHttpOnly(true);  // 보안 ↑
    accessCookie.setPath("/");
    accessCookie.setMaxAge(60 * 60); // 1시간

    response.addCookie(accessCookie);

    return ResponseEntity.status(HttpStatus.FOUND)
            .header("Location", "/main")
            .build();
}



    @PatchMapping(value = "/logout")
    public ResponseEntity<String> logoutKakao() {
        try {
            boolean result = oAuthLoginService.logout();
            if (result) {
                return ResponseEntity.ok("로그아웃 성공");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그아웃 실패");
            }
        } catch (Exception e) {
            log.error("카카오 로그아웃 오류: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃 처리 중 오류 발생");
        }
    }

    @DeleteMapping(value = "/withdraw")
    public ResponseEntity<String> withdraw() {
        oAuthLoginService.withdraw();
        return ResponseEntity.ok("회원탈퇴 성공");
    }
}
