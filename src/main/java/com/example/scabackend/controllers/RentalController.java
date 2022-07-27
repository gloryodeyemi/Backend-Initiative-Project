package com.example.scabackend.controllers;

import com.example.scabackend.models.Rentals;
import com.example.scabackend.services.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("rentals")
public class RentalController {
    @Autowired
    RentalsService rentalsService;

    @PostMapping
    public ResponseEntity<Rentals> createRentals(@Valid @RequestBody Rentals rental) {
        return ResponseEntity.ok(rentalsService.save(rental));
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
    public void deleteRental(@PathVariable Long id) {
        rentalsService.deleteById(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Rentals> updateRental(@PathVariable Long id, @RequestBody Rentals rental) {
        return rentalsService.update(id, rental);
    }
}
