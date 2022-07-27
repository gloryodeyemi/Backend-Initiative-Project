package com.example.scabackend.controllers;

import com.example.scabackend.models.Reviews;
import com.example.scabackend.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    ReviewsService reviewsService;

    @PostMapping
    public ResponseEntity<Reviews> createReviews(@Valid @RequestBody Reviews review) {
        return ResponseEntity.ok(reviewsService.save(review));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reviews>> findAll(){
        return ResponseEntity.ok(reviewsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reviews> findById(@PathVariable Long id){
        return ResponseEntity.ok(reviewsService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewsService.deleteById(id);
    }

//    @PatchMapping("/update/{id}")
//    public ResponseEntity<Reviews> updateReview(@PathVariable Long id, @RequestBody Reviews review) {
//        return reviewsService.updateR(id, review);
//    }
}
