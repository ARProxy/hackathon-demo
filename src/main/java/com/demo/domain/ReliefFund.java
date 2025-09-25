package com.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "relief_fund")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReliefFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 지원금 제목
    @Column(nullable = false, length = 200)
    private String title;

    // 기관명
    @Column(nullable = false, length = 100)
    private String agency;

    // 설명
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
