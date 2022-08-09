package com.example.scabackend.services;

import com.example.scabackend.models.Movies;
import com.example.scabackend.models.RStatus;
import com.example.scabackend.models.Rentals;
import com.example.scabackend.repositories.MoviesRepository;
import com.example.scabackend.repositories.RentalsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalsService implements CrudService<Rentals, Long>{
    @Autowired
    RentalsRepository rentalsRepository;

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    MoviesService moviesService;

    public Rentals save(Rentals rental){
        Movies movie = moviesRepository.findById(rental.getMovieId()).get();
        if (movie.getQuantity() < rental.getNoOfCopies()){
            throw new RuntimeException("Error-Please try a lesser number of copies. Quantity available = " + movie.getQuantity());
        }
        Double amount = movie.getRentalFee() * rental.getNoOfCopies();
        rental.setCurrency("NGN");
        rental.setAmount(amount);
        rental.setRStatus(RStatus.RENTED);
        int movieQuantityLeft = movie.getQuantity() - rental.getNoOfCopies();
        movie.setQuantity(movieQuantityLeft);
        movie.setStatus(moviesService.getStatus(movieQuantityLeft));
        movie.setId(rental.getMovieId());
        moviesRepository.save(movie);
        return rentalsRepository.save(rental);
    }

    @Override
    public void delete(Rentals object) {

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

    public void deleteById(Long id) {
        rentalsRepository.deleteById(id);
    }

    @Override
    public Page<Rentals> findAllByPage(Pageable pageable) {
        return null;
    }
}
