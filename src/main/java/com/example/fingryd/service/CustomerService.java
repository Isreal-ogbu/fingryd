package com.example.fingryd.service;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Customer;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.ChangePin;
import com.example.fingryd.modelValidator.Registration;
import com.example.fingryd.repository.CustomerAccountsRepository;
import com.example.fingryd.repository.CustomerRepository;
import com.example.fingryd.service.managers.CustomerManager;
import com.example.fingryd.utils.ReportUtil;
import com.example.fingryd.utils.findrydMail.FingrydMail;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService implements CustomerManager {
    private final CustomerRepository customerRepository;
    private final CustomerAccountsRepository customerAccountsRepository;
    private final ReportUtil reportUtil;
    private final FingrydMail fingrydMail;
    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           ReportUtil reportUtil,
                           CustomerAccountsRepository customerAccountsRepository,
                           FingrydMail fingrydMail
                           ){
        this.customerRepository = customerRepository;
        this.reportUtil = reportUtil;
        this.customerAccountsRepository= customerAccountsRepository;
        this.fingrydMail = fingrydMail;
    }

    @Transactional
    public ResponseEntity<Map<String, String>> createCustomerAccount(Registration e) {
        Map<String, String> response = new HashMap<>();
        Optional<Customer> c = customerRepository.findByMobile(e.getMobile());
        if (c.isPresent()){
            response.put("message", "Customer with phone number already Exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Customer customer = new Customer();
        customer.setName(e.getName());
        customer.setMobile(e.getMobile());
        customer.setEmail(e.getEmail());
        customer.setUserName(e.getUserName());
        customer.setPassword(e.getPassword());
        customer.setAddress(e.getAddress());
        Customer customer1 = customerRepository.save(customer);
        double initialBalance = 0;
        CustomerAccounts customerAccounts = new CustomerAccounts(customer1, e.getMobile().substring(4), e.getPin(), e.getAccount_type(), initialBalance);
        customerAccountsRepository.save(customerAccounts);
        reportUtil.accountCreationReport(e.getName());
        fingrydMail.welcomeEmail(e.getEmail(), e.getName());
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
    @Transactional
    public ResponseEntity<String> deleteAccount(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerException("Customer with id does not exist"));
        customerRepository.delete(customer);
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

}
