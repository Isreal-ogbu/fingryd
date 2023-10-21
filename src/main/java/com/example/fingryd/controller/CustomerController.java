package com.example.fingryd.controller;

import com.example.fingryd.model.Customer;
import com.example.fingryd.modelValidator.ChangePin;
import com.example.fingryd.modelValidator.Registration;
import com.example.fingryd.service.CustomerService;
import com.example.fingryd.utils.findrydMail.FingrydMail;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/c/")
public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping("/{id}/")
    public ResponseEntity<Customer> getCustomerInformation(@PathVariable Long id){

        return customerService.getAccount(id);
    }
    @DeleteMapping("/{id}/")
    public ResponseEntity<String> deleteCustomerInformation(@PathVariable Long id){

        return customerService.deleteAccount(id);
    }

    @PostMapping("/change-pin/")
    public ResponseEntity<Map<String, String>> changePin( @RequestBody @Valid ChangePin changePin) {

        return customerService.changePin(changePin);
    }

}
