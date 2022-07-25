package com.example.scabackend.services;

import com.example.scabackend.models.AuthenticationProvider;
import com.example.scabackend.models.Users;
import com.example.scabackend.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    public ResponseEntity<Users> createUser(Users user) {
//        return ResponseEntity.ok(usersRepository.save(user));
//    }

    public void processOAuthPostLogin(String username) {
        Users existUser = usersRepository.getUserByUsername(username);

        if (existUser == null) {
            Users newUser = new Users();
            newUser.setUsername(username);
            newUser.setAuthProvider(AuthenticationProvider.FACEBOOK);
//            newUser.setEnabled(true);
            usersRepository.save(newUser);
        }

    }

    public ResponseEntity<List<Users>> createUsers(List<Users> users){
        List<Users> userList = new ArrayList<>();
        for (Users user: users){
//            log.info("user::{}", user);
            String password = passwordEncoder.encode(user.getPassword());
            if (user.getPassword().equals(user.getConfirmPassword())) {
                user.setPassword(password);
                userList.add(usersRepository.save(user));
            } else {
//                throw new AccountException("Password error-Password does not match!");
            }
        }
//        log.info("user list::{}", userList);
        return ResponseEntity.ok(userList);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public ResponseEntity<Users> updateUser(Long id, Users user){
//        log.info("user::{}", user);
        Users userToUpdate = findById(id);
        BeanUtils.copyProperties(user, userToUpdate);
        userToUpdate.setId(id);
//        log.info("user::{}", userToUpdate);
        return ResponseEntity.ok(usersRepository.save(userToUpdate));
    }

    public String deleteById(Long userId) {
        usersRepository.deleteById(userId);
        return "User deleted!";
    }
}
