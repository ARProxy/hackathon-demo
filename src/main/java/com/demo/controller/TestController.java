package com.demo.controller;

import com.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    /**
     * 로그인 페이지 (기본 진입점)
     */
    @GetMapping("/")
    public String loginPage() {
        return "login"; // login.html
    }

    /**
     * 카카오 OAuth Redirect → code 값 받음
     */
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code, Model model) {
        // code를 callback.html에 넘겨서 JS fetch로 백엔드 API 호출
        model.addAttribute("code", code);
        return "callback"; // callback.html (스피너 화면)
    }
}
