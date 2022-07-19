package com.example.scabackend.repositories;

import com.example.scabackend.models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {
}
