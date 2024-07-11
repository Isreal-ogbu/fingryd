package com.example.fingryd.controller;

import com.example.fingryd.modelValidator.Credit;
import com.example.fingryd.modelValidator.Purchase;
import com.example.fingryd.modelValidator.Transfer;
import com.example.fingryd.modelValidator.Withdrawal;
import com.example.fingryd.service.TransactonService;
import com.example.fingryd.service.managers.TransactionManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/t/")
public class TransactionController {

    private final TransactionManager transactionManager;
    @Autowired
    public TransactionController(TransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    @PostMapping("withdraw/")
    public ResponseEntity<String> withdrawalFromAccount(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid Withdrawal withdrawal){

        return transactionManager.withdrawFromAccount(withdrawal);
    }

    @PostMapping("credit/")
    public ResponseEntity<Map<String, String>> creditAccount(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid Credit credit){

        return transactionManager.creditAccount(credit);
    }

    @PostMapping("transfer/")
    public ResponseEntity<Map<String, String>> transfer(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid Transfer transfer){

        return transactionManager.transferFromAccount(transfer);
    }

    @PostMapping("buy/")
    public ResponseEntity<Map<String, String>> purchaseItem(
            @RequestHeader HttpHeaders httpHeaders,
            @RequestBody @Valid Purchase purchase){

        return transactionManager.purchaseItem(purchase);
    }

}
