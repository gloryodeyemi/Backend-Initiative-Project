package com.example.scabackend.controllers;

import com.example.scabackend.models.Movies;
import com.example.scabackend.models.Users;
import com.example.scabackend.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MoviesService moviesService;

    @PostMapping
    public ResponseEntity<Movies> createMovies(@Valid @RequestBody Movies movie) {
        return ResponseEntity.ok(moviesService.save(movie));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movies>> findAll(){
        return ResponseEntity.ok(moviesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movies> findById(@PathVariable Long id){
        return ResponseEntity.ok(moviesService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        moviesService.deleteById(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Movies> updateMovie(@PathVariable Long id, @RequestBody Movies movie) {
        return moviesService.update(id, movie);
    }
}
