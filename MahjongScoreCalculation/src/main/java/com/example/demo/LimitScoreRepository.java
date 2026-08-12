package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitScoreRepository extends JpaRepository<LimitScore, Long> {
    LimitScore findByName(String name);
}
