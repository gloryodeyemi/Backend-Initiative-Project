package com.example.scabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Movie title cannot be blank")
    private String title;

    @NotBlank(message = "Movie description cannot be blank")
    private String description;

    @ManyToMany
//    @NotBlank(message = "Movie genre cannot be blank")
    @JsonIgnoreProperties({"id", "createdAt", "updatedAt"})
    private List<Genre> genre;

    @NotNull(message = "Maturity rating id is mandatory")
    @Positive(message = "Id must be positive")
    private Long maturityRatingId;

    @NotBlank(message = "Director cannot be blank")
    private String director;

    @NotBlank(message = "Writer cannot be blank")
    private String writer;

    @NotBlank(message = "Producer cannot be blank")
    private String producer;
//    private String ratings;

    @NotBlank(message = "Duration cannot be blank")
    private String duration;

    private MovieQuality movieQuality;

    @NotBlank(message = "Casts cannot be blank")
    private String casts;

    @NotNull(message = "Rental fee is mandatory")
    @PositiveOrZero(message = "Rental fee must be zero or positive")
    private Double rentalFee;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @OneToOne
    @JsonIgnoreProperties({"id", "user", "movies", "createdOn", "updatedAt"})
    private Media moviePoster;

    @OneToMany
    @JsonIgnoreProperties({"id", "movieId", "createdAt"})
    private List<Reviews> reviews;

    @PositiveOrZero(message = "Quantity must be zero or positive")
    @Column(nullable = false)
    private int quantity;

    private QStatus status;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
