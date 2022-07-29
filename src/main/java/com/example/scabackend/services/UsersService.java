package com.example.scabackend.services;

import com.cloudinary.Cloudinary;
import com.example.scabackend.dto.PasswordDto;
import com.example.scabackend.exceptions.AccountException;
import com.example.scabackend.models.AuthenticationProvider;
import com.example.scabackend.models.Media;
import com.example.scabackend.models.UserRoles;
import com.example.scabackend.models.Users;
import com.example.scabackend.repositories.MediaRepository;
import com.example.scabackend.repositories.UserRolesRepository;
import com.example.scabackend.repositories.UsersRepository;
import com.example.scabackend.security.CustomOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UsersService implements CrudService<Users, Long>{
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    UserRolesRepository userRolesRepository;

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
            newUser.setRoles(findRole());
            newUser.setAuthProvider(AuthenticationProvider.THIRD_PARTY);
            usersRepository.save(newUser);
        }
    }

//    public ResponseEntity<List<Users>> createUsers(List<Users> users){
//        List<Users> userList = new ArrayList<>();
//        for (Users user: users){
////            log.info("user::{}", user);
//            String password = passwordEncoder.encode(user.getPassword());
//            if (user.getPassword().equals(user.getConfirmPassword())) {
//                user.setPassword(password);
//                userList.add(usersRepository.save(user));
//            } else {
////                throw new AccountException("Password error-Password does not match!");
//            }
//        }
////        log.info("user list::{}", userList);
//        return ResponseEntity.ok(userList);
//    }

    public Users save(Users users) throws RuntimeException {
        if (usersRepository.existsByEmailAddress(users.getEmailAddress())){
            throw new AccountException("Error-An account with " + users.getEmailAddress() + " already exists.");
        }
        String password = passwordEncoder.encode(users.getPassword());
        if (users.getPassword().equals(users.getConfirmPassword())) {
            users.setPassword(password);
            users.setAuthProvider(AuthenticationProvider.LOCAL);
            users.setRoles(findRole());
            return usersRepository.save(users);
        } else {
            throw new RuntimeException("Password error-Password does not match!");
        }
    }

    public void changePassword(PasswordDto passwordDto, Long userId){
        Users users = findById(userId);
        String password = passwordEncoder.encode(passwordDto.getNewPassword());
        if (!(passwordEncoder.matches(passwordDto.getOldPassword(), users.getPassword()))){
            throw new AccountException("Error-Your old password is not correct!");
        }
        if (!(passwordDto.getNewPassword().equals(passwordDto.getConfirmNewPassword()))){
            throw new AccountException("Password error-Password does not match!");
        }
        if (passwordEncoder.matches(passwordDto.getNewPassword(), users.getPassword())){
            throw new AccountException("Password error-You cannot use your previous password!");
        }
        users.setPassword(password);
        users.setId(userId);
        usersRepository.save(users);
    }

    public Set<UserRoles> findRole(){
        UserRoles userRole = userRolesRepository.findByName("USER");
        Set<UserRoles> rolesSet = new HashSet<>();
        rolesSet.add(userRole);
        return rolesSet;
    }

    @Override
    public void delete(Users object) {

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

    public void deleteById(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public Page<Users> findAllByPage(Pageable pageable) {
        return null;
    }

    public void saveProfilePicture(Users users, String url, String title, String message){
        Media newPicture = new Media();
        newPicture.setImageUrl(url);
        newPicture.setTitle(title);
        newPicture.setUser(users);
        newPicture.setMessage(message);
        Media savedPicture = mediaRepository.save(newPicture);
        users.setProfilePicture(savedPicture);
        users.setId(users.getId());
        usersRepository.save(users);
    }
}
