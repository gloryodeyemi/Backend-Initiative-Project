package com.example.scabackend.repositories;

import com.example.scabackend.models.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicturesRepository extends JpaRepository<Pictures, Long> {
    Pictures findPicturesByImageUrl(String imageUrl);
}
