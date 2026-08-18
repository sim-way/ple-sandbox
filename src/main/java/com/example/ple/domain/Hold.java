package com.example.ple.domain;

import java.time.LocalDate;
import java.util.UUID;
import com.example.ple.domain.annotations.AggregateRoot;

@AggregateRoot
public class Hold {
    private UUID holdId;
    private UUID bookId;
    private UUID patronId;
    private LocalDate placedDate;
    private LocalDate expirationDate;
    private Integer queuePosition;
}
