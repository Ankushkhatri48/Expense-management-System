package com.khatri.Kharcha_Management_System.service;

import com.khatri.Kharcha_Management_System.model.AppUser;
import com.khatri.Kharcha_Management_System.model.Expense;
import com.khatri.Kharcha_Management_System.repository.ExpenseRepository;
import com.khatri.Kharcha_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService{

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private UserRepository userRepository;

    private AppUser getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("Logged-in-user-not found"));
    }


    public Expense addExpense(Expense expense){
        expense.setUser(getCurrentUser());
        return repository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.findByUser(getCurrentUser());
    }

    public Expense getExpenseById(Long id){
        return repository.findByIdAndUser(id,getCurrentUser()).orElse(null);
    }

    public Expense updateExpense(Long id, Expense updatedExpense){
        Optional<Expense> optionalExpense = repository.findByIdAndUser(id,getCurrentUser());
            if(optionalExpense.isPresent()){
                Expense existingexpense = optionalExpense.get();
                existingexpense.setTitle(updatedExpense.getTitle());
                existingexpense.setCategory(updatedExpense.getCategory());
                existingexpense.setAmount(updatedExpense.getAmount());
                existingexpense.setDate(updatedExpense.getDate());
                return repository.save(existingexpense);
            }
        return null;
    }

    public boolean deleteExpense(Long id){
        Optional<Expense> optionalExpense = repository.findByIdAndUser(id,getCurrentUser());
        if(optionalExpense.isPresent()){
            repository.delete(optionalExpense.get());
            return true;
        }
        return false;
    }
}
