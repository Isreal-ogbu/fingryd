package com.example.fingryd.model;

import com.example.fingryd.model.model_enum.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"account_id", "accountNumber"}))
public final class CustomerAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long account_id;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    @NotEmpty(message = "name cannot be empty")
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", nullable = false, unique = false)
    private Customer customer_id;

    @NotNull(message = "phone number cannot be null")
    @NotBlank(message = "phone number cannot be blank")
    @NotEmpty(message = "phone number cannot be empty")
    private String accountNumber;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @NotEmpty(message = "email cannot be empty")
    @DecimalMin(message = "balance must be of the form ~0.00", value = "2")
    private double balance = 0.00;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @NotEmpty(message = "email cannot be empty")
    @Length(
            min = 4,
            max = 4
    )
    private int pin;

    @NotNull(message = "account type be null")
    @NotBlank(message = "account type cannot be blank")
    @NotEmpty(message = "account type cannot be empty")
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    private LocalDate created = LocalDate.now();
}
