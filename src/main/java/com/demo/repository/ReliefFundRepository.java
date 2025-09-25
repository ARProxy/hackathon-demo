package com.demo.repository;

import com.demo.domain.ReliefFund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReliefFundRepository extends JpaRepository<ReliefFund, Long> {
    Optional<ReliefFund> findByTitle(String title);
}
