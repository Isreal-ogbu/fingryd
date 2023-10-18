package com.example.fingryd.repository;

import com.example.fingryd.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserName(String userName);
    Optional<Employee> findByEmployeeId(UUID employeeId);
    Optional<Employee> deleteByEmployeeId(UUID employeeId);
}
