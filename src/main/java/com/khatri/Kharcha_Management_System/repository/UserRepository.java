package com.khatri.Kharcha_Management_System.repository;

import com.khatri.Kharcha_Management_System.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

}
