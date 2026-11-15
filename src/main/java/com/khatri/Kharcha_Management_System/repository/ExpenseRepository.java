package com.khatri.Kharcha_Management_System.repository;

import com.khatri.Kharcha_Management_System.model.AppUser;
import com.khatri.Kharcha_Management_System.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(AppUser user);
    Optional<Expense> findByIdAndUser(Long id, AppUser user);
}
