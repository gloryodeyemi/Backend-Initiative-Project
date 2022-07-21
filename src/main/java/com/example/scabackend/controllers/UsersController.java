package com.example.scabackend.controllers;

import com.example.scabackend.models.Users;
import com.example.scabackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    UsersService usersServices;

//    @PostMapping()
//    public ResponseEntity<Users> createUser(@RequestBody Users user) {
//        return usersServices.createUser(user);
//    }

    @PostMapping()
    public ResponseEntity<List<Users>> createUsers(@Valid @RequestBody List<Users> users) {
        return usersServices.createUsers(users);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.ok(usersServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id){
        return ResponseEntity.ok(usersServices.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(usersServices.deleteById(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @Valid @RequestBody Users user) {
        return usersServices.updateUser(id, user);
    }
}
