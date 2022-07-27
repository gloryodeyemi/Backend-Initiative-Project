package com.example.scabackend.controllers;

import com.example.scabackend.models.Genre;
import com.example.scabackend.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("genre")
public class GenreController {
    @Autowired
    GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@Valid @RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.save(genre));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> findAll(){
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findById(@PathVariable Long id){
        return ResponseEntity.ok(genreService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteById(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        return genreService.update(id, genre);
    }
}
