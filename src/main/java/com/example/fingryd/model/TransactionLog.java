package com.example.fingryd.model;

import com.example.fingryd.model.model_enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customer_Transaction")
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transaction_id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String reference_number;

    private double amount;

    private long debitor_id;

    private String debitorName;

    private long creditor_id;

    private String creditorName;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    private LocalDateTime transaction_start_time = LocalDateTime.now();

    private LocalDateTime transaction_end_time;

}
