package com.example.scabackend.services;

import com.example.scabackend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;


}
