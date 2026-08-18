package com.example.ple.domain;

import java.util.UUID;
import com.example.ple.domain.annotations.AggregateRoot;

@AggregateRoot
public class Book {
    private UUID bookId;
    private String title;
    private Boolean restricted;
}
