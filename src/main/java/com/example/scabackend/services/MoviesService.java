package com.example.scabackend.services;

import com.example.scabackend.models.Movies;
import com.example.scabackend.repositories.MoviesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoviesService {
    @Autowired
    MoviesRepository moviesRepository;

    public ResponseEntity<List<Movies>> save(List<Movies> movies){
        return ResponseEntity.ok(moviesRepository.saveAll(movies));
    }

    public List<Movies> findAll() {
        return moviesRepository.findAll();
    }

    public Movies findById(Long id) {
        return moviesRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Movies> update(Long id, Movies movie){
        Movies movieToUpdate = findById(id);
        BeanUtils.copyProperties(movie, movieToUpdate);
        movieToUpdate.setId(id);
        return ResponseEntity.ok(moviesRepository.save(movieToUpdate));
    }

    public String deleteById(Long id) {
        moviesRepository.deleteById(id);
        return "Movie deleted!";
    }
}
