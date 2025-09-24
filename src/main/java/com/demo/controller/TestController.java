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

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("problems", testService.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam String title,
            @RequestParam String description
    ) {
        testService.save(title, description);
        return "redirect:/";
    }
}
