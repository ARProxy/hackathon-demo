package com.demo.common;

import com.demo.domain.User;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMember {

    private final UserRepository userRepository;

    public User getCurrentMember() {
        return userRepository
                .findByEmail(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .orElseThrow();
    }
}