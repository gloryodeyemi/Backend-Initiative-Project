package com.example.scabackend.dto;

import com.example.scabackend.models.Genre;
import com.example.scabackend.models.MovieQuality;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDto {
    private String title;
    private String description;
    private Long[] genreId;
    private Long maturityRatingId;
    private String director;
    private String writer;
    private String producer;
    private String duration;
    private MovieQuality movieQuality;
    private String casts;
    private Double rentalFee;
    private LocalDate releaseDate;
}
