package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KoScoreRepository extends JpaRepository<KoScore, Long> {
    KoScore findByFuAndHan(int fu, int han);
}
