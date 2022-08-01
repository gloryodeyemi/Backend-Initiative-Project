package com.example.scabackend.controllers;

import com.example.scabackend.dto.MovieDto;
import com.example.scabackend.models.Movies;
import com.example.scabackend.models.Users;
import com.example.scabackend.services.MediaService;
import com.example.scabackend.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MoviesService moviesService;

    @Autowired
    MediaService mediaService;

    @PostMapping
    public ResponseEntity<Movies> createMovies(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(moviesService.save(movieDto));
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
    public ResponseEntity<Movies> updateMovie(@PathVariable Long id, @RequestBody MovieDto movie) {
        return moviesService.update(id, movie);
    }

    @PostMapping("/upload/{movieId}")
    public ResponseEntity<LinkedHashMap<String, Object>> uploadMoviePoster(@PathVariable("movieId") Long movieId, @RequestParam("file") MultipartFile file,
                                                                              @RequestParam("title") String title, @RequestParam("message") String message) throws IOException {
        Movies currentMovie = moviesService.findById(movieId);
        String url = mediaService.uploadMedia(file);
        moviesService.addPoster(currentMovie, url, title, message);

        LinkedHashMap<String, Object> jsonResponse = mediaService.modifyJsonResponse("create", url);
        return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
    }
}
