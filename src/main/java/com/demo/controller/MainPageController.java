package com.demo.controller;

import com.demo.domain.User;
import com.demo.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @GetMapping("/main")
    public String mainPage(Model model) {
        // 로그인한 사용자 정보 가져오기 (DB or SecurityContext)
        User user = mainPageService.getCurrentUser();

        model.addAttribute("user", user);
        return "main"; // templates/main.html
    }
}
