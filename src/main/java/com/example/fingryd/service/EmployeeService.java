package com.example.fingryd.service;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Employee;
import com.example.fingryd.modelValidator.UpdateDetail;
import com.example.fingryd.repository.EmployeeRepository;
import com.example.fingryd.requests.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository e){
        employeeRepository = e;
    }

    public ResponseEntity<Map<String, String>> getEmployeeById(UUID employeeId){

        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()-> new CustomerException("Provided details is Incorrect"));

        return ResponseEntity.status(HttpStatus.OK).body(employee.getDetail());
    }
    public ResponseEntity<Map<String, List<Map<String, String>>>> getAllEmployee(){

        Map<String, List<Map<String, String>>> response = new HashMap<>();
        List<Employee> employee = employeeRepository.findAll();
        List<Map<String, String>> sortedEmployee = employee.stream().map(Employee:: getDetail).toList();
        response.put("Fingryd Staff", sortedEmployee);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<Map<String, String>> addEmployee(RegistrationRequest e){

        Employee employee = new Employee(e.getName(), e.getUserName(), e.getEmail(), e.getMobile(), e.getPassword(), e.getAddress(), e.getRole());
        employeeRepository.save(employee);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee record created");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<Map<String, String>> updateEmployeeDetails(UUID id, UpdateDetail e){

        Employee employee = employeeRepository.findByEmployeeId(id).orElseThrow(()-> new CustomerException("Employee does not exist"));
        employee.setName(Objects.requireNonNullElse(e.getName(), employee.getName()));
        employee.setUserName(Objects.requireNonNullElse(e.getUserName(), employee.getUsername()));
        employee.setEmail(Objects.requireNonNullElse(e.getEmail(), employee.getEmail()));
        employee.setMobile(Objects.requireNonNullElse(e.getMobile(), employee.getMobile()));
        employee.setAddress(Objects.requireNonNullElse(e.getAddress(), employee.getAddress()));
        employeeRepository.save(employee);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee record updated");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<Map<String, String>> deleteEmployeeDetails(UUID id){

        employeeRepository.deleteByEmployeeId(id).orElseThrow(()-> new CustomerException("Employee not found"));
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee record deleted successfully");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
