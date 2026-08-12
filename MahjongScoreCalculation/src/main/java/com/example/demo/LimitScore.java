package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "LIMIT_SCORE")
public class LimitScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int childRon;
    private int childTsumoChild;
    private int childTsumoParent;

    private int parentRon;
    private int parentTsumoChild;

    // getter/setter
}
