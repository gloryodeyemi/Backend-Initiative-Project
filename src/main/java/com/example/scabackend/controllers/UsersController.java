package com.example.scabackend.controllers;

import com.example.scabackend.models.Users;
import com.example.scabackend.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    UsersServices usersServices;

//    @PostMapping()
//    public ResponseEntity<Users> createUser(@RequestBody Users user) {
//        return usersServices.createUser(user);
//    }

    @PostMapping()
    public ResponseEntity<List<Users>> createUsers(@RequestBody List<Users> users) {
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
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        return usersServices.updateUser(id, user);
    }
}
