package com.example.scabackend.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String username;
    private String password;
    private String confirmPassword;
}
