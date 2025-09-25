package com.demo.service;

import com.demo.common.GetMember;
import com.demo.domain.ReliefFund;
import com.demo.domain.User;
import com.demo.repository.ReliefFundRepository;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final UserRepository userRepository;
    private final ReliefFundRepository reliefFundRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("로그인한 사용자가 DB에 없습니다: " + email));
    }

    // 지원금 저장
    @Transactional
    public ReliefFund saveFund(String title, String agency, String description) {
        ReliefFund fund = ReliefFund.builder()
                .title(title)
                .agency(agency)
                .description(description)
                .build();
        return reliefFundRepository.save(fund);
    }

    // 지원금 상세 조회
    @Transactional(readOnly = true)
    public ReliefFund getFundByTitle(String title) {
        return reliefFundRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("지원금 정보를 찾을 수 없습니다: " + title));
    }
}
