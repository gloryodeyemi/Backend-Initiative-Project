package com.example.scabackend.services;

import com.example.scabackend.dto.AssignRoleDto;
import com.example.scabackend.models.UserRoles;
import com.example.scabackend.models.Users;
import com.example.scabackend.repositories.UserRolesRepository;
import com.example.scabackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRolesService implements CrudService<UserRoles, Long> {
    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<UserRoles> findAll() {
        return userRolesRepository.findAll();
    }

    @Override
    public UserRoles findById(Long aLong) {
        return userRolesRepository.findById(aLong).orElse(null);
    }

    @Override
    public UserRoles save(UserRoles object) {
        return userRolesRepository.save(object);
    }

    public void assignRole(AssignRoleDto assignRoleDto) throws Exception{
        Optional<Users> user = usersRepository.findByEmailAddress(assignRoleDto.getUserEmail());
        UserRoles role = findById(assignRoleDto.getRoleId());
//        ArrayList<UserRoles> rolesToAssign = new ArrayList<>();
        if (user.isPresent()){
            Set<UserRoles> rolesSet = user.get().getRoles();
            if (rolesSet.contains(role)){
                throw new Exception("Cannot assign role-Role exists!");
            }
            rolesSet.add(role);
            user.get().setRoles(rolesSet);
            user.get().setId(user.get().getId());
            usersRepository.save(user.get());
        } else {
            throw new Exception("Error-User not found!");
        }
    }

    public void removeRole(AssignRoleDto assignRoleDto) throws Exception{
        Optional<Users> user = usersRepository.findByEmailAddress(assignRoleDto.getUserEmail());
        UserRoles role = findById(assignRoleDto.getRoleId());
        if (user.isPresent()){
            Set<UserRoles> userRoles = user.get().getRoles();
            if (userRoles.contains(role)){
                userRoles.remove(role);
                user.get().setRoles(userRoles);
                user.get().setId(user.get().getId());
                usersRepository.save(user.get());
            }
        } else {
            throw new Exception("Error-User not found!");
        }
    }

    @Override
    public void delete(UserRoles object) {

    }

    @Override
    public void deleteById(Long aLong) {
        userRolesRepository.deleteById(aLong);
    }

    @Override
    public Page<UserRoles> findAllByPage(Pageable pageable) {
        return null;
    }
}
