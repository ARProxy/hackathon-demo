package com.demo.service;

import com.demo.domain.Tests;
import com.demo.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;

    public List<Tests> findAll() {
        return repository.findAll();
    }

    public Tests save(String title, String description) {
        Tests problem = Tests.builder()
                .title(title)
                .description(description)
                .build();
        return repository.save(problem);
    }
}
