package com.example.scabackend.services;

import com.example.scabackend.dto.MovieDto;
import com.example.scabackend.models.*;
import com.example.scabackend.repositories.GenreRepository;
import com.example.scabackend.repositories.MediaRepository;
import com.example.scabackend.repositories.MoviesRepository;
import com.example.scabackend.repositories.ReviewsRepository;
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
    GenreRepository genreRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    public Movies save(Movies object){
        return null;
    }

    public Movies save(MovieDto movieDto) {
        Movies movie = new Movies();
        BeanUtils.copyProperties(movieDto, movie);
        movie.setGenre(getMovieGenre(movieDto.getGenreId()));
        movie.setStatus(getStatus(movieDto.getQuantity()));
        return moviesRepository.save(movie);
    }

    public List<Genre> getMovieGenre(Long[] genreId){
        List<Genre> genres = new ArrayList<>();
        for (Long i: genreId){
            Genre genre = genreRepository.findById(i).orElse(null);
            genres.add(genre);
        }
        return genres;
    }

    public QStatus getStatus(int quantity){
        if (quantity > 5){
            return QStatus.AVAILABLE;
        } else if (quantity > 0){
            return QStatus.LIMITED_QUANTITY;
        } else {
            return QStatus.OUT_OF_STOCK;
        }
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

    public ResponseEntity<Movies> update(Long id, MovieDto movie){
        Movies movieToUpdate = findById(id);
        BeanUtils.copyProperties(movie, movieToUpdate);
        movieToUpdate.setGenre(getMovieGenre(movie.getGenreId()));
        movieToUpdate.setStatus(getStatus(movie.getQuantity()));
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

    public List<Reviews> getMovieReviews(Long movieId) {
        return reviewsRepository.findByMovieId(movieId);
    }
}
