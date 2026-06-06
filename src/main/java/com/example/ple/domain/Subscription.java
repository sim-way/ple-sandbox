package com.example.ple.domain;

import java.util.UUID;
import com.example.ple.domain.annotations.AggregateRoot;

@AggregateRoot
public class Subscription {
    private UUID id;
    private String status;
    private String planType;
}
