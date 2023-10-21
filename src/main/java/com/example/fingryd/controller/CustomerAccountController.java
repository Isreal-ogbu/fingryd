package com.example.fingryd.controller;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.AccountNumberValidator;
import com.example.fingryd.service.CustomerAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/a/")
public class CustomerAccountController {
    private final CustomerAccountService customerAccountService;
    @Autowired
    private CustomerAccountController(CustomerAccountService customerAccountService){

        this.customerAccountService = customerAccountService;
    }
    @PostMapping("customer-account/")
    public ResponseEntity<CustomerAccounts> getCustomerAccount(@RequestBody @Valid AccountNumberValidator e){

        return customerAccountService.getCustomerAccountByAccountNumber(e);
    }
}
