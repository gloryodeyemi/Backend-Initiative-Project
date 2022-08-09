package com.example.scabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Movie id must be positive")
    private Long movieId;

    @Positive(message = "Movie id must be positive")
    private Long userId;

    @Column(nullable = false)
    private String currency;

    private Double amount;

    @Positive(message = "Number of copies must be positive")
    private int noOfCopies;

    @NotNull
    private RStatus rStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
