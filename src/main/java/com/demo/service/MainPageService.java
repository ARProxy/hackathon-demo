package com.demo.service;

import com.demo.common.GetMember;
import com.demo.domain.User;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final UserRepository userRepository;
    private final GetMember getMember;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("로그인한 사용자가 DB에 없습니다: " + email));
    }
}
