package com.example.fingryd.service;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Customer;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.Credit;
import com.example.fingryd.modelValidator.Purchase;
import com.example.fingryd.modelValidator.Transfer;
import com.example.fingryd.modelValidator.Withdrawal;
import com.example.fingryd.repository.CustomerAccountsRepository;
import com.example.fingryd.utils.Charges;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @Transactional
    public ResponseEntity<String> withdrawFromAccount(Withdrawal withdrawal){

        CustomerAccounts customerAccounts = customerAccountsRepository.findByAccountNumber(Long.parseLong(withdrawal.getCustomerAccountNumber())).orElseThrow(()-> new CustomerException("Customer with the account does not exist"));

        if(customerAccounts.getPin() != Long.parseLong(withdrawal.getSenderPin())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect pin. Check and Try again");
        }

        if (customerAccounts.getBalance() < (withdrawal.getAmount() + Charges.getSAME_BANK_CHARGES())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Balance");
        }

        customerAccounts.setBalance(customerAccounts.getBalance()-(withdrawal.getAmount()+Charges.getSAME_BANK_CHARGES()));
        customerAccountsRepository.save(customerAccounts);

        return ResponseEntity.ok("Dear customer, a withdrawal of "+ withdrawal.getAmount() + " was successfully deducted from your account");
    }
    @Transactional
    public ResponseEntity<Map<String, String>> creditAccount(Credit credit){

        CustomerAccounts customerAccounts = customerAccountsRepository.findByAccountNumber(Long.parseLong(credit.getCustomerAccountNumber())).orElseThrow(
                ()->new CustomerException("Account number does not exist"));

        customerAccounts.setBalance(customerAccounts.getBalance() + credit.getAmount());
        customerAccountsRepository.save(customerAccounts);

        Map<String, String> message = new HashMap<>();
        message.put("message", "Success");
        message.put("Transaction message", "Successfully deposited "+ credit.getAmount() + " into account with account number " +
                customerAccounts.getAccountNumber().toString().substring(0, 5) + "***");

        return ResponseEntity.ok(message);
    }
    @Transactional
    public ResponseEntity<Map<String, String>> transferFromAccount(Transfer transfer){

        Map<String, String> response = new HashMap<>();
        Withdrawal withdrawal = new Withdrawal(transfer.getSenderAccountNumber(),
                transfer.getAmount(), transfer.getSenderPin());
        HttpStatusCode status = withdrawFromAccount(withdrawal).getStatusCode();

        if (status == HttpStatusCode.valueOf(200)){

            Credit credit = new Credit(transfer.getReceiverAccountNumber(), transfer.getAmount());
            HttpStatusCode code = creditAccount(credit).getStatusCode();

            if(code == HttpStatusCode.valueOf(200)){
                response.put("message", "Transaction successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        response.put("message", "Transaction Failed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity<Map<String, String>> purchaseItem(Purchase purchase){

        Map<String, String> response = new HashMap<>();
        Withdrawal withdrawal = new Withdrawal(purchase.getCustomerAccountNumber(), purchase.getTotalItemPrice(),purchase.getSenderPin());
        HttpStatusCode code = withdrawFromAccount(withdrawal).getStatusCode();

        if (code == HttpStatusCode.valueOf(200)){

            response.put("message", "Item successfully purchased");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        response.put("message", "Insufficient balance to make payment. Try later");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
