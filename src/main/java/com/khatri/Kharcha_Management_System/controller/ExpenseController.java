package com.khatri.Kharcha_Management_System.controller;

import com.khatri.Kharcha_Management_System.model.Expense;
import com.khatri.Kharcha_Management_System.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    // Home / Dashboard Page
    @GetMapping("/")
    public String dashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Expense> expenses = service.getAllExpenses();
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();

        model.addAttribute("username", username);
        model.addAttribute("expenses", expenses);
        model.addAttribute("totalAmount", total);
        model.addAttribute("today", LocalDate.now());
        return "dashboard"; // dashboard.html kholega
    }

    // Add Expense from HTML Form
    @PostMapping("/expenses/add")
    public String addExpense(@Valid @ModelAttribute Expense expense, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/?error";
        }
        service.addExpense(expense);
        return "redirect:/";
    }
    // Update Expense from Edit Modal
    @PostMapping("/expenses/update/{id}")
    public String updateExpense(@PathVariable Long id, @Valid @ModelAttribute Expense expense, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/?error";
        }
        service.updateExpense(id, expense);
        return "redirect:/";
    }

    // Delete Expense
    @GetMapping("/expenses/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return "redirect:/";
    }
}