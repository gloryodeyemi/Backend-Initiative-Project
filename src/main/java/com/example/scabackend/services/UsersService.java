package com.example.scabackend.services;

import com.example.scabackend.models.AuthenticationProvider;
import com.example.scabackend.models.Users;
import com.example.scabackend.repositories.UsersRepository;
import com.example.scabackend.security.CustomOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void processOAuthPostLogin(String username, String authName, CustomOAuth2User oAuth2User) {
        Optional<Users> existUser = usersRepository.findByEmailAddress(username);
        System.out.println(authName);
        System.out.println(oAuth2User.getAttributes());
        String[] fullName = authName.split(" ");
        log.info("user:" + existUser);

        if (existUser.isEmpty()) {
            Users newUser = new Users();
            newUser.setEmailAddress(username);
            newUser.setFirstName(fullName[0]);
            newUser.setLastName(fullName[1]);

            newUser.setAuthProvider(AuthenticationProvider.THIRD_PARTY);
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
