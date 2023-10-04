package com.example.fingryd.controller;

import com.example.fingryd.modelValidator.Credit;
import com.example.fingryd.modelValidator.Withdrawal;
import com.example.fingryd.service.TransactonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("transaction/V1/")
public class TransactionController {
    private TransactonService transactonService;
    @Autowired
    public TransactionController(TransactonService transactonService){
        this.transactonService = transactonService;
    }
    @PostMapping("withdral/")
    public ResponseEntity<String> widrawlFromAccount(@RequestBody @Valid Withdrawal withdrawal){
        return transactonService.withdrawFromAccount(withdrawal);
    }
    @PostMapping("credit/")
    public ResponseEntity<Map<String, String>> creditAccount(@RequestBody @Valid Credit credit){
        return transactonService.creditAccount(credit);
    }
}
