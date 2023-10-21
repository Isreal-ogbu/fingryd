package com.example.fingryd.controller;

import com.example.fingryd.requests.AuthenticationRequest;
import com.example.fingryd.requests.RegistrationRequest;
import com.example.fingryd.response.AuthenticationResponse;
import com.example.fingryd.service.EmployeeService;
import com.example.fingryd.service.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/fin/")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    private EmployeeService employeeService;
    @Autowired
    public AuthController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @PostMapping("login/")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
    @PostMapping(value = "add-employee/")
    public ResponseEntity<Map<String, String>> addEmployee(@RequestBody @Valid RegistrationRequest registrationRequest){

        return employeeService.addEmployee(registrationRequest);
    }
}
