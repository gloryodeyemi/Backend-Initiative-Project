package com.example.scabackend.services;

import com.example.scabackend.models.Genre;
import com.example.scabackend.models.MaturityRatings;
import com.example.scabackend.repositories.MaturityRatingsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaturityRatingsService implements CrudService<MaturityRatings, Long> {
    @Autowired
    MaturityRatingsRepository maturityRatingsRepository;

    @Override
    public List<MaturityRatings> findAll() {
        return maturityRatingsRepository.findAll();
    }

    @Override
    public MaturityRatings findById(Long aLong) {
        return maturityRatingsRepository.findById(aLong).orElse(null);
    }

    @Override
    public MaturityRatings save(MaturityRatings object) {
        return maturityRatingsRepository.save(object);
    }

    @Override
    public void delete(MaturityRatings object) {

    }

    @Override
    public void deleteById(Long aLong) {
        maturityRatingsRepository.deleteById(aLong);
    }

    public ResponseEntity<MaturityRatings> update(Long id, MaturityRatings maturityRating){
        MaturityRatings ratingToUpdate = findById(id);
        BeanUtils.copyProperties(maturityRating, ratingToUpdate);
        ratingToUpdate.setId(id);
        return ResponseEntity.ok(maturityRatingsRepository.save(ratingToUpdate));
    }

    @Override
    public Page<MaturityRatings> findAllByPage(Pageable pageable) {
        return null;
    }
}
