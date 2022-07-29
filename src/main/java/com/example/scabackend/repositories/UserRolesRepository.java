package com.example.scabackend.repositories;

import com.example.scabackend.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    UserRoles findByName(String name);
}
