package com.khatri.Kharcha_Management_System.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private AppUser user;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message="Title cannot be empty")
    String title;

    @Positive(message = "Amount should be greater than 0")
    double amount;

    @NotBlank(message="Category should be there")
    String category;

    @NotNull(message = "Date should be there")
    @PastOrPresent(message = "Future dates are not allowed")
    LocalDate date;
}
