package com.demo.repository;

import com.demo.domain.Tests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Tests, Long> {
}
