package com.example.fingryd.service;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.AccountNumberValidator;
import com.example.fingryd.repository.CustomerAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountService {
    private CustomerAccountsRepository customerAccountsRepository;
    @Autowired
    public CustomerAccountService(CustomerAccountsRepository customerAccountsRepository){
        this.customerAccountsRepository = customerAccountsRepository;
    }

    public ResponseEntity<CustomerAccounts> getCustomerAccountByAccountNumber(AccountNumberValidator accountNumberValidator){
        CustomerAccounts customerAccounts = customerAccountsRepository.findByAccountNumber(Long.parseLong(
                accountNumberValidator.getCustomerAccountNumber())).orElseThrow(()-> new CustomerException("Account does not exist. Check account number"));
        customerAccounts.setPin(null);
        customerAccounts.getCustomerId().setPassword("*******");
        return ResponseEntity.status(HttpStatus.OK).body(customerAccounts);
    }
}
