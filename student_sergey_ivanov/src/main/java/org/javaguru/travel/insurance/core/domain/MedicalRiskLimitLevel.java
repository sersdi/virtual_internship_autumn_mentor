package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class MedicalRiskLimitLevel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medical_risk_limit_level_ic", nullable = false)
    private String medicalRiskLimitLevelIc;

    @Column(name = "coefficient", precision = 10, scale = 2, nullable = false)
    private BigDecimal coefficient;
}