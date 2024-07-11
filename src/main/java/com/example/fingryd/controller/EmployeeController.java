package com.example.fingryd.controller;

import com.example.fingryd.modelValidator.UpdateDetail;
import com.example.fingryd.requests.RegistrationRequest;
import com.example.fingryd.service.EmployeeService;
import com.example.fingryd.service.managers.EmployeeManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/e/")
public class EmployeeController {
    private final EmployeeManager employeeManager;

    @Autowired
    private EmployeeController(EmployeeManager employeeManager){

        this.employeeManager = employeeManager;
    }
    @GetMapping(value = "get-employee/{employeeId}")
    public ResponseEntity<Map<String, String>> getEmployee(
            @RequestHeader HttpHeaders httpHeaders,
            @PathVariable @Valid UUID employeeId){

        return employeeManager.getEmployeeById(employeeId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get-all-employee/")
    public ResponseEntity<Map<String, List<Map<String, String>>>> getEmployee(){

        return employeeManager.getAllEmployee();
    }
    @PutMapping(value = "update-employee-detail/{id}")
    public ResponseEntity<Map<String, String>> updateEmployeeDetail(
            @RequestHeader HttpHeaders httpHeaders,
            @PathVariable @Valid UUID id,
            @RequestBody @Valid UpdateDetail updateDetail){

        return employeeManager.updateEmployeeDetails(id,updateDetail);
    }
    @PostMapping (value = "add-employee/")
    public ResponseEntity<Map<String, String>> addEmployee(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid RegistrationRequest registrationRequest){

        return employeeManager.addEmployee(registrationRequest);
    }
    @DeleteMapping(value = "remove-employee/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(
            @RequestHeader HttpHeaders httpHeaders,
            @PathVariable UUID id){

        return employeeManager.deleteEmployeeDetails(id);
    }

}
