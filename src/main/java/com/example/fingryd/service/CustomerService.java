package com.example.fingryd.service;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Customer;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.ChangePin;
import com.example.fingryd.modelValidator.Registration;
import com.example.fingryd.repository.CustomerAccountsRepository;
import com.example.fingryd.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Configuration
public class CustomerService {
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAccountsRepository customerAccountsRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    public ResponseEntity<String> createCustomerAccount(Registration e){

        Customer customer = new Customer(e.getName(), e.getMobile(), e.getEmail(), e.getUserName(), e.getPassword(),e.getAddress());
        Customer customer1 = customerRepository.save(customer);
        CustomerAccounts customerAccounts = new CustomerAccounts(customer1, e.getMobile().substring(3,13), e.getPin(), e.getAccount_type());
        customerAccountsRepository.save(customerAccounts);

        return ResponseEntity.status(HttpStatus.CREATED).body("Welcome to Fingryd bank, Your account" +
                "Was successfully created. Kindly check your email for account information");
    }
    public ResponseEntity<String> updateAccountInformation(){ return ResponseEntity.ok("");}
    public ResponseEntity<Customer> getAccount(Long id){

        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerException("Customer with id does nor exist"));
        customer.setPassword("*******");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    public ResponseEntity<String> deleteAccount(Long id){
//        will need more
        customerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account with "+ id + " has been deleted successfully");
    }
    @Transactional
    public ResponseEntity<Map<String, String>> changePin(ChangePin changePin ){
        Map<String, String> response = new HashMap<>();
        CustomerAccounts customer = customerAccountsRepository.findByAccountNumberAndPin(Long.parseLong(changePin.getCustomerAccountNumber()),
                Long.parseLong(changePin.getCustomerPin())).orElseThrow(()-> new CustomerException("Incorrect Account number or pin"));
        customer.setPin(Long.parseLong(changePin.getCustomerNewPin()));
        customerAccountsRepository.save(customer);
        response.put("message", "Password successfully changed");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Transactional
    public ResponseEntity<Map<String, String>> changeCustomerDetails(){
        Map<String, String> response = new HashMap<>();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
