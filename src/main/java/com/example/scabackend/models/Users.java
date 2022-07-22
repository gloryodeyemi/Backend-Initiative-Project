package com.example.scabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Validation error-First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Validation error-Last name cannot be blank")
    private String lastName;

    @Column(unique = true)
    @NotNull(message = "Validation error-Email address is mandatory")
    @Email(message = "Validation error-Email should be valid")
    private String emailAddress;

    @Column(unique = true)
    @NotBlank(message = "Validation error-Phone number is mandatory")
    @Size(min = 8, max = 20, message
            = "Validation error-Phone number must be between 8 and 20 characters")
    private String phoneNumber;

    @Column(unique = true)
    @NotBlank(message = "Validation error-Username cannot be blank")
    @Size(min = 6, max = 20, message
            = "Validation error-Username must be between 6 and 20 characters")
    private String username;

    @NotNull(message = "Validation error-Password is mandatory")
    @Size(min = 8, message
            = "Validation error-Password cannot be less than 8 characters")
    private String password;

    @Transient
    @NotNull(message = "Validation error-Confirm password is mandatory")
    @Size(min = 8, message
            = "Validation error-Password cannot be less than 8 characters")
    private String confirmPassword;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
