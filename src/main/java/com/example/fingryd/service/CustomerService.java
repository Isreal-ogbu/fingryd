package com.example.fingryd.service;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Customer;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.ChangePin;
import com.example.fingryd.modelValidator.Registration;
import com.example.fingryd.repository.CustomerAccountsRepository;
import com.example.fingryd.repository.CustomerRepository;
import com.example.fingryd.utils.ReportUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Configuration
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    private CustomerAccountsRepository customerAccountsRepository;
    @Autowired
    private ReportUtil reportUtil;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    public ResponseEntity<Map<String, String>> createCustomerAccount(Registration e) {
        Map<String, String> response = new HashMap<>();
        Optional<Customer> c = customerRepository.findByMobile(e.getMobile());
        if (c.isPresent()){
            response.put("message", "Customer with phone number already Exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Customer customer = new Customer(e.getName(), e.getMobile(), e.getEmail(), e.getUserName(), e.getPassword(), e.getAddress());
        Customer customer1 = customerRepository.save(customer);
        double initialBalance = 0;
        CustomerAccounts customerAccounts = new CustomerAccounts(customer1, e.getMobile().substring(4), e.getPin(), e.getAccount_type(), initialBalance);
        customerAccountsRepository.save(customerAccounts);
        reportUtil.accountCreationReport(e.getName());
        response.put("message", "Welcome to Fingryd bank, Your account" +
                "Was successfully created. Kindly check your email for account information");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    public ResponseEntity<String> updateAccountInformation(){
        return ResponseEntity.ok("");
    }
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
    public ResponseEntity<Map<String, String>> changePin(ChangePin changePin ) {
        Map<String, String > response = new HashMap<>();
        CustomerAccounts customerAccounts = customerAccountsRepository.findByAccountNumber(Long.parseLong(changePin.getCustomerAccountNumber()))
                .orElseThrow(() -> new CustomerException("Customer accounts not found"));
        if (!customerAccounts.getPin().equals(Long.parseLong(changePin.getCustomerPin()))) {
            response.put("message", "Incorrect current PIN. Please try again." );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        customerAccounts.setPin(Long.valueOf(changePin.getCustomerNewPin()));
        customerAccountsRepository.save(customerAccounts);
        response.put("message", "PIN successfully changed.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    public ResponseEntity<Map<String, String>> changeCustomerDetails(){
        Map<String, String> response = new HashMap<>();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
