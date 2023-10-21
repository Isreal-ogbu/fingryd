package com.example.fingryd.controller;

import com.example.fingryd.modelValidator.UpdateDetail;
import com.example.fingryd.requests.RegistrationRequest;
import com.example.fingryd.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/e/")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    private EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping(value = "get-employee/{employeeId}")
    public ResponseEntity<Map<String, String>> getEmployee(@PathVariable @Valid UUID employeeId){

        return employeeService.getEmployeeById(employeeId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get-all-employee/")
    public ResponseEntity<Map<String, List<Map<String, String>>>> getEmployee(){

        return employeeService.getAllEmployee();
    }
    @PutMapping(value = "update-employee-detail/{id}")
    public ResponseEntity<Map<String, String>> updateEmployeeDetail(@PathVariable @Valid UUID id, @RequestBody @Valid UpdateDetail updateDetail){

        return employeeService.updateEmployeeDetails(id,updateDetail);
    }
    @PostMapping (value = "add-employee/")
    public ResponseEntity<Map<String, String>> addEmployee(@RequestBody @Valid RegistrationRequest registrationRequest){

        return employeeService.addEmployee(registrationRequest);
    }
    @DeleteMapping(value = "remove-employee/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable UUID id){

        return employeeService.deleteEmployeeDetails(id);
    }

}
