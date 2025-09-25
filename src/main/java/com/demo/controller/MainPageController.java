package com.demo.controller;

import com.demo.domain.ReliefFund;
import com.demo.domain.User;
import com.demo.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/start")
    public String starts(Model model) {
        // 로그인한 사용자 정보 가져오기 (DB or SecurityContext)
        User user = mainPageService.getCurrentUser();

        model.addAttribute("user", user);
        return "start"; // templates/main.html
    }

    @GetMapping("/relief/detail")
    public String reliefDetail(
            @RequestParam String title,
            @RequestParam String agency,
            @RequestParam String description,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("agency", agency);
        model.addAttribute("description", description);
        return "relief-detail.html"; // templates/relief-detail.html.html
    }

    @PostMapping("/relief/apply")
    public String applyRelief(@RequestParam String title, Model model) {
        // TODO: 신청 로직 (DB insert, 외부 API 연동 등)
        model.addAttribute("message", title + " 신청이 완료되었습니다!");
        return "relief-success"; // 신청 완료 페이지
    }

    // 상세 페이지 이동
    @GetMapping("/relief-detail")
    public String showReliefDetail(@RequestParam String title,
                                   @RequestParam String agency,
                                   @RequestParam String description,
                                   Model model) {
        // DB에 해당 데이터가 없으면 저장
        ReliefFund fund;
        try {
            fund = mainPageService.getFundByTitle(title);
        } catch (IllegalArgumentException e) {
            fund = mainPageService.saveFund(title, agency, description);
        }

        model.addAttribute("title", fund.getTitle());
        model.addAttribute("agency", fund.getAgency());
        model.addAttribute("description", fund.getDescription());

        User user = mainPageService.getCurrentUser();
        model.addAttribute("user", user);

        return "relief-detail.html"; // Thymeleaf template
    }

    @GetMapping("/apply")
    public String applyForm(Model model) {
        User user = mainPageService.getCurrentUser();
        model.addAttribute("user", user);
        return "apply"; // 위에서 만든 apply.html
    }
}
