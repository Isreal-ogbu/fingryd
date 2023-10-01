package com.example.fingryd.modelValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Purchase {
    @NotNull(message = "customer account number cannot be null")
    @NotBlank(message = "customer account number cannot be blank")
    @NotEmpty(message = "customer account number cannot be empty")
    private long customerAccountNumber;

    @NotNull(message = "item price cannot be null")
    @NotBlank(message = "item price cannot be blank")
    @NotEmpty(message = "item price cannot be empty")
    private double totalItemPrice;

    @NotNull(message = "pin number cannot be null")
    @NotBlank(message = "pin number cannot be blank")
    @NotEmpty(message = "pin number cannot be empty")
    private int senderPin;

    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be blank")
    @NotEmpty(message = "description cannot be empty")
    private String itemDescription;
}
