package com.example.scabackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
}
