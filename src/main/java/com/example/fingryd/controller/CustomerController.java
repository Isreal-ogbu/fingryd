package com.example.fingryd.controller;

import com.example.fingryd.model.Customer;
import com.example.fingryd.modelValidator.ChangePin;
import com.example.fingryd.service.*;
import com.example.fingryd.service.managers.CustomerManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/c/")
public class CustomerController {

    private final CustomerManager customerManager;
    @Autowired
    public CustomerController(CustomerManager customerManager){
        this.customerManager = customerManager;
    }
    @GetMapping("/{id}/")
    public ResponseEntity<Customer> getCustomerInformation(
            @RequestHeader HttpHeaders httpHeaders,
            @PathVariable Long id){

        return customerManager.getAccount(id);
    }
    @DeleteMapping("delete/{id}/")
    public ResponseEntity<String> deleteCustomerInformation(
            @RequestHeader HttpHeaders httpHeaders,
            @PathVariable Long id){

        return customerManager.deleteAccount(id);
    }

    @PostMapping("/change-pin/")
    public ResponseEntity<Map<String, String>> changePin(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid ChangePin changePin) {

        return customerManager.changePin(changePin);
    }

}
