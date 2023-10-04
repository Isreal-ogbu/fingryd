package com.example.fingryd.modelValidator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfer {
    @NotNull(message = "sender account number cannot be null")
    @NotBlank(message = "sender account number cannot be blank")
    @NotEmpty(message = "sender account number cannot be empty")
    private long senderAccountNumber;

    @NotNull(message = "receiver account number cannot be null")
    @NotBlank(message = "receiver account number cannot be blank")
    @NotEmpty(message = "receiver account number cannot be empty")
    private long receiverAccountNumber;

    @NotNull(message = "amount cannot be null")
    @NotBlank(message = "amount cannot be blank")
    @NotEmpty(message = "amount cannot be empty")
    private double amount;

    @NotNull(message = "pin number cannot be null")
    @NotBlank(message = "pin number cannot be blank")
    @NotEmpty(message = "pin number cannot be empty")
    private int senderPin;

    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be blank")
    @NotEmpty(message = "description cannot be empty")
    private String description;
    
}
