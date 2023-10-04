package com.example.fingryd.model;

import com.example.fingryd.model.model_enum.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"accountId", "accountNumber"}))
public final class CustomerAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerId", nullable = false, unique = false)
    private Customer customerId;

    private Long accountNumber;

    private Double balance = 0.00;

    private Long pin;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    private LocalDate created = LocalDate.now();

    public CustomerAccounts(Customer customerId, String substring, String pin, AccountType accountType) {
        this.customerId= customerId;
        this.accountNumber = Long.parseLong(substring);
        this.pin = Long.parseLong(pin);
        this.accountType = Objects.requireNonNullElse(accountType, AccountType.SAVINGS);
    }
}
