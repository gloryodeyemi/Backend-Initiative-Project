package com.example.scabackend.services;

import com.example.scabackend.models.Media;
import com.example.scabackend.models.Movies;
import com.example.scabackend.models.Users;
import com.example.scabackend.repositories.MediaRepository;
import com.example.scabackend.repositories.MoviesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoviesService implements CrudService<Movies, Long>{
    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    MediaRepository mediaRepository;

    public Movies save(Movies movie){
        return moviesRepository.save(movie);
    }

    @Override
    public void delete(Movies object) {

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

    public void deleteById(Long id) {
        moviesRepository.deleteById(id);
    }

    @Override
    public Page<Movies> findAllByPage(Pageable pageable) {
        return null;
    }

    public void addPoster(Movies movie, String url, String title, String message){
        Media newPoster = new Media();
        newPoster.setImageUrl(url);
        newPoster.setTitle(title);
        newPoster.setMovies(movie);
        newPoster.setMessage(message);
        Media savedPoster = mediaRepository.save(newPoster);
        movie.setMoviePoster(savedPoster);
        movie.setId(movie.getId());
        moviesRepository.save(movie);
    }
}
