package com.example.scabackend.repositories;

import com.example.scabackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmailAddress(String email);

    Boolean existsByEmailAddress(String email);

//    @Query("SELECT u FROM Users u WHERE u.username = :username")
//    public Optional<Users> getUserByUsername(@Param("username") String username);
}
