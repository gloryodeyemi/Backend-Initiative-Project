package com.example.scabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Movies {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @OneToMany
    private List<Genre> genre;

    private Long maturityRatingId;

    private String director;
    private String writer;
    private String producer;
//    private String ratings;
    private String duration;
    private MovieQuality movieQuality;
    private String casts;
    private Double rentalFee;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @OneToMany
    private List<Reviews> reviews;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
