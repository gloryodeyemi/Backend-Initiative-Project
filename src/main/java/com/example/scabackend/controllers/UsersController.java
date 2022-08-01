package com.example.scabackend.controllers;

import com.cloudinary.Cloudinary;
import com.example.scabackend.dto.PasswordDto;
import com.example.scabackend.dto.UserDto;
import com.example.scabackend.models.Users;
import com.example.scabackend.services.MediaService;
import com.example.scabackend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    UsersService usersServices;

    @Autowired
    MediaService mediaService;

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

    @PostMapping("/add")
    public ResponseEntity<Users> createUser(@RequestBody UserDto userDto) throws RuntimeException {
        return ResponseEntity.ok(usersServices.save(userDto));
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
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        return usersServices.updateUser(id, user);
    }

    @PatchMapping("/change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
        usersServices.changePassword(passwordDto, id);
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<LinkedHashMap<String, Object>> uploadProfilePicture(@PathVariable("userId") Long userId,  @RequestParam("file") MultipartFile file,
                                                                              @RequestParam("title") String title, @RequestParam("message") String message) throws IOException {
//         Users currentUser = usersServices.findUserByEmail(authentication.getName()); // Authorization
        Users currentUser = usersServices.findById(userId);
        String url = mediaService.uploadMedia(file);
        usersServices.saveProfilePicture(currentUser, url, title, message);

         LinkedHashMap<String, Object> jsonResponse = mediaService.modifyJsonResponse("create", url);
        return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
    }
}
