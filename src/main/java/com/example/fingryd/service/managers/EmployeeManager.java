package com.example.fingryd.service.managers;

import com.example.fingryd.modelValidator.UpdateDetail;
import com.example.fingryd.requests.RegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface EmployeeManager  {
    ResponseEntity<Map<String, String>> getEmployeeById(UUID employeeId);
    ResponseEntity<Map<String, List<Map<String, String>>>> getAllEmployee();
    ResponseEntity<Map<String, String>> addEmployee(RegistrationRequest e);
    ResponseEntity<Map<String, String>> updateEmployeeDetails(UUID id, UpdateDetail e);
    ResponseEntity<Map<String, String>> deleteEmployeeDetails(UUID id);
}
