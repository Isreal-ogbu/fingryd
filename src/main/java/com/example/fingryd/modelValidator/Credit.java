package com.example.fingryd.modelValidator;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
public class Credit {
    @NotNull(message = "customer account number cannot be null")
    @NotBlank(message = "customer account number cannot be blank")
    @NotEmpty(message = "customer account number cannot be empty")
    @Length(min = 10, max = 10, message="Account number is incorrect. Enter a valid account number")
    private String customerAccountNumber;

    @NotNull
    @DecimalMin(value = "1.0", message = "Value must be at least 1.0")
    private Double amount;
}
