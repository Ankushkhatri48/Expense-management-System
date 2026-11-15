package com.khatri.Kharcha_Management_System.controller;

import com.khatri.Kharcha_Management_System.model.AppUser;
import com.khatri.Kharcha_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html kholega
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html kholega
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "User Already Exists!");
            return "register";
        }
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/login?registered";
    }
}