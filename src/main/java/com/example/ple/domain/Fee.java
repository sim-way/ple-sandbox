package com.example.ple.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import com.example.ple.domain.annotations.AggregateRoot;

@AggregateRoot
public class Fee {
    private UUID feeId;
    private UUID patronId;
    private UUID loanId;
    private BigDecimal amount;
    private LocalDate accruedDate;
    private BigDecimal paidAmount;
}
