package com.example.scabackend.controllers;

import com.example.scabackend.models.Rentals;
import com.example.scabackend.services.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rentals")
public class RentalController {
    @Autowired
    RentalsService rentalsService;

    @PostMapping
    public ResponseEntity<List<Rentals>> createRentals(@RequestBody List<Rentals> rentals) {
        return rentalsService.save(rentals);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rentals>> findAll(){
        return ResponseEntity.ok(rentalsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rentals> findById(@PathVariable Long id){
        return ResponseEntity.ok(rentalsService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalsService.deleteById(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Rentals> updateRental(@PathVariable Long id, @RequestBody Rentals rental) {
        return rentalsService.update(id, rental);
    }
}
