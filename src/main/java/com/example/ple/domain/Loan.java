package com.example.ple.domain;

import java.time.LocalDate;
import java.util.UUID;
import com.example.ple.domain.annotations.AggregateRoot;

@AggregateRoot
public class Loan {
    private UUID loanId;
    private UUID bookId;
    private UUID patronId;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}
