package com.example.fingryd.controller;

import com.example.fingryd.modelValidator.Registration;
import com.example.fingryd.requests.AuthenticationRequest;
import com.example.fingryd.response.AuthenticationResponse;
import com.example.fingryd.service.*;
import com.example.fingryd.service.EmployeeService;
import com.example.fingryd.service.AuthenticationService;
import com.example.fingryd.service.managers.AuthManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/account/")
public class AuthController {

    private final AuthManager authManager;
    private final CustomerService customerService;

    @Autowired
    public AuthController(CustomerService customerService,
                          EmployeeService employeeService,
                          AuthenticationService authManager){
        this.authManager = authManager;
        this.customerService = customerService;
    }
    @PostMapping("login/")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authManager.authenticate(authenticationRequest));
    }

    @PostMapping("create-account/")
    public ResponseEntity<Map<String, String>> creatCustomerAccount(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid Registration registration){
        return customerService.createCustomerAccount(registration);
    }
}
