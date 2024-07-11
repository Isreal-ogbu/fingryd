package com.example.fingryd.service.managers;

import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.AccountNumberValidator;
import org.springframework.http.ResponseEntity;

public interface CustomerAccountManager {
    ResponseEntity<CustomerAccounts> getCustomerAccountByAccountNumber(AccountNumberValidator accountNumberValidator);
}
