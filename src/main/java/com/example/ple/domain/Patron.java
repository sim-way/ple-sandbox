package com.example.ple.domain;

import java.math.BigDecimal;
import java.util.UUID;
import com.example.ple.domain.annotations.AggregateRoot;

@AggregateRoot
public class Patron {
    private UUID patronId;
    private String name;
    private Boolean researcherStatus;
    private BigDecimal unpaidFeeBalance;
}
