package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OyaScoreRepository extends JpaRepository<OyaScore, Long> {
    OyaScore findByFuAndHan(int fu, int han);
}
