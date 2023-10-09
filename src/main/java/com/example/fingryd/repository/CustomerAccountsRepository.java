package com.example.fingryd.repository;

import com.example.fingryd.model.Customer;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.model.model_enum.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountsRepository extends JpaRepository<CustomerAccounts, Long> {
    void deleteByCustomerId(Customer customer);
    Optional<CustomerAccounts> findByAccountNumber(Long accountNumber);


}
