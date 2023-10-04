package com.example.fingryd.service;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.Credit;
import com.example.fingryd.modelValidator.Withdrawal;
import com.example.fingryd.repository.CustomerAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class TransactonService {

    @Autowired
    private CustomerAccountsRepository customerAccountsRepository;

    public ResponseEntity<String> withdrawFromAccount(Withdrawal withdrawal){
        CustomerAccounts customerAccounts = customerAccountsRepository.findByAccountNumber(Long.parseLong(withdrawal.getCustomerAccountNumber())).orElseThrow(()-> new CustomerException("Customer with the account does not exist"));
        if(customerAccounts.getPin() != Long.parseLong(withdrawal.getSenderPin())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect pin. Check and Try again");
        }
        if (customerAccounts.getBalance() < withdrawal.getAmount()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient fund in user's account");
        }
        customerAccounts.setBalance(customerAccounts.getBalance() -withdrawal.getAmount());
        customerAccountsRepository.save(customerAccounts);
        return ResponseEntity.ok("Dear customer, a withdrawal of "+ withdrawal.getAmount() + " was successfully deducted from your account");
    }
    public ResponseEntity<Map<String, String>> creditAccount(Credit credit){
        CustomerAccounts customerAccounts = customerAccountsRepository.findByAccountNumber(Long.parseLong(credit.getCustomerAccountNumber())).orElseThrow(
                ()->new CustomerException("Account number does not exist"));
        customerAccounts.setBalance(customerAccounts.getBalance() + credit.getAmount());
        customerAccountsRepository.save(customerAccounts);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Success");
        message.put("Transaction messsage", "Successfully deposited "+ credit.getAmount() + " into account with account number" +
                customerAccounts.getAccountNumber().toString().substring(0, 5) + "***");
        return ResponseEntity.ok(message);
    }
    public ResponseEntity<String> transferFromAccount(){

        return ResponseEntity.ok("");
    }

    public ResponseEntity<String> purchaseItem(){

        return ResponseEntity.ok("");
    }
}
