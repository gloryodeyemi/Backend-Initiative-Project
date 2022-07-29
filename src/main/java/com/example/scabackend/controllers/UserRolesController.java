package com.example.scabackend.controllers;

import com.example.scabackend.dto.AssignRoleDto;
import com.example.scabackend.models.UserRoles;
import com.example.scabackend.services.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("roles")
public class UserRolesController {
    @Autowired
    UserRolesService userRolesService;

    @PostMapping
    public ResponseEntity<UserRoles> createUserRoles(@Valid @RequestBody UserRoles userRole) {
        return ResponseEntity.ok(userRolesService.save(userRole));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserRoles>> findAll(){
        return ResponseEntity.ok(userRolesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoles> findById(@PathVariable Long id){
        return ResponseEntity.ok(userRolesService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteUserRole(@PathVariable Long id) {
        userRolesService.deleteById(id);
    }

//    @PatchMapping("/update/{id}")
//    public ResponseEntity<UserRoles> updateUserRole(@PathVariable Long id, @RequestBody UserRoles userRole) {
//        return userRolesService.update(id, userRole);
//    }
    @PatchMapping("/assign/{userId}")
    public void assignRole(@PathVariable Long userId, @RequestBody AssignRoleDto assignRoleDto) throws Exception{
        userRolesService.assignRole(assignRoleDto, userId);
    }

    @PatchMapping("/remove/{userId}")
    public void removeRole(@PathVariable Long userId, @RequestBody AssignRoleDto assignRoleDto) throws Exception{
        userRolesService.removeRole(assignRoleDto, userId);
    }
}
