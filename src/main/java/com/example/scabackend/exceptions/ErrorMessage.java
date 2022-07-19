package com.example.scabackend.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {
    private String title;
    private String message;
}
