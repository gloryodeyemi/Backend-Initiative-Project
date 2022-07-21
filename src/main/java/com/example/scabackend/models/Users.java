package com.example.scabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull(message = "Email address is mandatory")
    @Email(message = "Email should be valid")
    private String emailAddress;

    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 8, max = 20, message
            = "Phone number must be between 8 and 20 characters")
    private String phoneNumber;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 6, max = 20, message
            = "Username must be between 6 and 20 characters")
    private String username;

    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message
            = "Password cannot be less than 8 characters")
    private String password;

    @Transient
    @NotNull(message = "Confirm password is mandatory")
    @Size(min = 8, message
            = "Password cannot be less than 8 characters")
    private String confirmPassword;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
