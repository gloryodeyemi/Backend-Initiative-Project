package com.example.scabackend.controllers;

import com.example.scabackend.dto.PasswordDto;
import com.example.scabackend.models.Users;
import com.example.scabackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String userIndex() {
        return "index";
    }

    @GetMapping("/logout_user")
    public String logoutPage(){
        return "redirect:/login";
    }

//    @PostMapping("/create")
//    public ResponseEntity<List<Users>> createUsers(@Valid @RequestBody List<Users> users) {
//        return usersServices.createUsers(users);
//    }

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users users) {
        return ResponseEntity.ok(usersServices.save(users));
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
    public void deleteUser(@PathVariable Long id) {
        usersServices.deleteById(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @Valid @RequestBody Users user) {
        return usersServices.updateUser(id, user);
    }

    @PatchMapping("/change-password/{id}")
    public void changePassword(@PathVariable Long id, @Valid @RequestBody PasswordDto passwordDto) {
        usersServices.changePassword(passwordDto, id);
    }
}
