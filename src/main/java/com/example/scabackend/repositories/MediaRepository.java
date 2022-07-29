package com.example.scabackend.repositories;

import com.example.scabackend.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findMediaByImageUrl(String imageUrl);
}
