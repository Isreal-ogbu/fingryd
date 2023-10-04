package com.example.fingryd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"customerId", "mobile", "email"}))
public final class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "phone number cannot be null")
    @NotBlank(message = "phone number cannot be blank")
    @NotEmpty(message = "phone number cannot be empty")
    @Pattern(regexp = "^\\+234\\d{10}$")
    private String mobile;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "must enter a valid email")
    private String email;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @NotEmpty(message = "username cannot be empty")
    private String userName;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @NotEmpty(message = "password cannot be empty")
    @Length(min = 8, max = 50, message = "min length of password is 8")
    private String password;

    @NotNull(message = "address cannot be null")
    @NotBlank(message = "address cannot be blank")
    @NotEmpty(message = "address cannot be empty")
    private String address;

    public Customer(String name, String mobile, String email, String userName, String password, String address) {
        this.name=name;
        this.mobile=mobile;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }
}
