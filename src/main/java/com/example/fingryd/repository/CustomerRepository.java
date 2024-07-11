package com.example.fingryd.repository;

import com.example.fingryd.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobile(String number);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUserName(String userName);
}
