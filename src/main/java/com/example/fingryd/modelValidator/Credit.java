package com.example.fingryd.modelValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Credit {
    @NotNull(message = "customer account number cannot be null")
    @NotBlank(message = "customer account number cannot be blank")
    @NotEmpty(message = "customer account number cannot be empty")
    private long customerAccountNumber;

    @NotNull(message = "amount cannot be null")
    @NotBlank(message = "amount cannot be blank")
    @NotEmpty(message = "amount cannot be empty")
    private double amount;
}
