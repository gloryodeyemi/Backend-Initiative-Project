package com.example.scabackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthenticationProvider authProvider;

    @NotNull
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"id", "createdAt", "updatedAt"})
    private Set<UserRoles> roles;

    @NotBlank(message = "Validation error-First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Validation error-Last name cannot be blank")
    private String lastName;

    @Column(unique = true)
    @NotNull(message = "Validation error-Email address is mandatory")
    @Email(message = "Validation error-Email should be valid")
    private String emailAddress;

    @Column(unique = true)
//    @NotBlank(message = "Validation error-Phone number is mandatory")
//    @Size(min = 8, max = 20, message
//            = "Validation error-Phone number must be between 8 and 20 characters")
    private String phoneNumber;

    @Column(unique = true)
//    @NotBlank(message = "Validation error-Username cannot be blank")
//    @Size(min = 6, max = 20, message
//            = "Validation error-Username must be between 6 and 20 characters")
    private String username;

    @OneToOne
    @JsonIgnoreProperties({"id", "user", "movies", "createdOn", "updatedAt"})
    private Media profilePicture;

    private String password;

    @Transient
    private String confirmPassword;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
