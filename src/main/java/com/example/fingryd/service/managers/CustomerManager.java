package com.example.fingryd.service.managers;

import com.example.fingryd.model.Customer;
import com.example.fingryd.modelValidator.ChangePin;
import com.example.fingryd.modelValidator.Registration;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CustomerManager {
    ResponseEntity<Map<String, String>> createCustomerAccount(Registration e);
    ResponseEntity<String> updateAccountInformation();
    ResponseEntity<Customer> getAccount(Long id);
    ResponseEntity<String> deleteAccount(Long id);
    ResponseEntity<Map<String, String>> changePin(ChangePin changePin );
}
