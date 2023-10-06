package com.example.fingryd.modelValidator;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Purchase {

    @NotNull(message = "pin number cannot be null")
    @NotBlank(message = "pin number cannot be blank")
    @NotEmpty(message = "pin number cannot be empty")
    private String customerAccountNumber;

    @NotNull
    @DecimalMin(value = "1.0", message = "Value must be at least 1.0")
    private Double totalItemPrice;

    @NotNull(message = "pin number cannot be null")
    @NotBlank(message = "pin number cannot be blank")
    @NotEmpty(message = "pin number cannot be empty")
    private String senderPin;

    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be blank")
    @NotEmpty(message = "description cannot be empty")
    private String itemDescription;
}
