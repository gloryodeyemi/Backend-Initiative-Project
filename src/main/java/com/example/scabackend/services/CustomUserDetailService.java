package com.example.scabackend.services;

import com.example.scabackend.models.AuthenticationProvider;
import com.example.scabackend.models.Users;
import com.example.scabackend.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByEmailAddress(username);
        if (user.isEmpty()) {
            System.out.println("user is empty");
            throw new RuntimeException(username + " not found");
//            throw new UsernameNotFoundException(username + " not found");
        }
        System.out.println();
        log.info("user: " + user);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        User theUser = new User(user.get().getEmailAddress(), user.get().getPassword(), grantedAuthorities);;
        log.info("theUser: " + theUser);
        return theUser;
    }
}
