package com.example.scabackend.repositories;

import com.example.scabackend.models.MaturityRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaturityRatingsRepository extends JpaRepository<MaturityRatings, Long> {
}
