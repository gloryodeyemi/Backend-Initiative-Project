package com.example.scabackend.services;

import com.example.scabackend.models.Reviews;
import com.example.scabackend.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService implements CrudService<Reviews, Long> {
    @Autowired
    ReviewsRepository reviewsRepository;

    @Override
    public List<Reviews> findAll() {
        return reviewsRepository.findAll();
    }

    @Override
    public Reviews findById(Long aLong) {
        return reviewsRepository.findById(aLong).orElse(null);
    }

    @Override
    public Reviews save(Reviews object) {
        return reviewsRepository.save(object);
    }

    @Override
    public void delete(Reviews object) {

    }

    @Override
    public void deleteById(Long aLong) {
        reviewsRepository.deleteById(aLong);
    }

    @Override
    public Page<Reviews> findAllByPage(Pageable pageable) {
        return null;
    }
}
