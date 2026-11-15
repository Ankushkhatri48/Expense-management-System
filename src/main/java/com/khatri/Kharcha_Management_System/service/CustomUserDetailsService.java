package com.khatri.Kharcha_Management_System.service;

import com.khatri.Kharcha_Management_System.model.AppUser;
import com.khatri.Kharcha_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not Found: "+ username));

        return new User(appUser.getUsername(),appUser.getPassword(), Collections.emptyList());

    }











}
