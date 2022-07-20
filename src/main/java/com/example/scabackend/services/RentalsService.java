package com.example.scabackend.services;

import com.example.scabackend.models.Movies;
import com.example.scabackend.models.Rentals;
import com.example.scabackend.repositories.RentalsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalsService {
    @Autowired
    RentalsRepository rentalsRepository;

    public ResponseEntity<List<Rentals>> save(List<Rentals> rentals){
        return ResponseEntity.ok(rentalsRepository.saveAll(rentals));
    }

    public List<Rentals> findAll() {
        return rentalsRepository.findAll();
    }

    public Rentals findById(Long id) {
        return rentalsRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Rentals> update(Long id, Rentals rental){
        Rentals rentToUpdate = findById(id);
        BeanUtils.copyProperties(rental, rentToUpdate);
        rentToUpdate.setId(id);
        return ResponseEntity.ok(rentalsRepository.save(rentToUpdate));
    }

    public String deleteById(Long id) {
        rentalsRepository.deleteById(id);
        return "Rent deleted!";
    }
}