package com.example.scabackend.services;

import com.example.scabackend.models.Genre;
import com.example.scabackend.models.Movies;
import com.example.scabackend.repositories.GenreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements CrudService<Genre, Long> {
    @Autowired
    GenreRepository genreRepository;


    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long aLong) {
        return genreRepository.findById(aLong).orElse(null);
    }

    @Override
    public Genre save(Genre object) {
        return genreRepository.save(object);
    }

    @Override
    public void delete(Genre object) {

    }

    @Override
    public void deleteById(Long aLong) {
        genreRepository.deleteById(aLong);
    }

    public ResponseEntity<Genre> update(Long id, Genre genre){
        Genre genreToUpdate = findById(id);
        BeanUtils.copyProperties(genre, genreToUpdate);
        genreToUpdate.setId(id);
        return ResponseEntity.ok(genreRepository.save(genreToUpdate));
    }

    @Override
    public Page<Genre> findAllByPage(Pageable pageable) {
        return null;
    }
}
