package com.example.scabackend.controllers;

import com.example.scabackend.models.MaturityRatings;
import com.example.scabackend.services.MaturityRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("m-rating")
public class MaturityRatingsController {
    @Autowired
    MaturityRatingsService maturityRatingsService;

    @PostMapping
    public ResponseEntity<MaturityRatings> createMaturityRating(@Valid @RequestBody MaturityRatings maturityRating) {
        return ResponseEntity.ok(maturityRatingsService.save(maturityRating));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaturityRatings>> findAll(){
        return ResponseEntity.ok(maturityRatingsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaturityRatings> findById(@PathVariable Long id){
        return ResponseEntity.ok(maturityRatingsService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteMaturityRating(@PathVariable Long id) {
        maturityRatingsService.deleteById(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MaturityRatings> updateMaturityRating(@PathVariable Long id, @RequestBody MaturityRatings maturityRating) {
        return maturityRatingsService.update(id, maturityRating);
    }
}
