package com.example.scabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Data
public class Reviews {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Movie id is mandatory")
    @Positive(message = "Id must be positive")
    private Long movieId;

    @NotNull(message = "User id is mandatory")
    @Positive(message = "Id must be positive")
    private Long userId;

    @NotBlank(message = "Review cannot be blank")
    private String review;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
