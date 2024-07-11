package com.example.fingryd.controller;
import com.example.fingryd.service.managers.CustomerAccountManager;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.AccountNumberValidator;
import com.example.fingryd.service.CustomerAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CustomerAccountController {
    private final CustomerAccountManager customerAccountService;
    @Autowired
    private CustomerAccountController(CustomerAccountManager customerAccountService){

        this.customerAccountService = customerAccountService;
    }
    @PostMapping("customer-account/")
    public ResponseEntity<CustomerAccounts> getCustomerAccount(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid AccountNumberValidator e){

        return customerAccountService.getCustomerAccountByAccountNumber(e);
    }
}
