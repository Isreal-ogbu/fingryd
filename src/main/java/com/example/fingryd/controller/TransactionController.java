package com.example.fingryd.controller;

import com.example.fingryd.modelValidator.Credit;
import com.example.fingryd.modelValidator.Purchase;
import com.example.fingryd.modelValidator.Transfer;
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
@RequestMapping("transaction/v1/")
public class TransactionController {
    private TransactonService transactonService;
    @Autowired
    public TransactionController(TransactonService transactonService){
        this.transactonService = transactonService;
    }

    @PostMapping("withdraw/")
    public ResponseEntity<String> withdrawalFromAccount(@RequestBody @Valid Withdrawal withdrawal){
        return transactonService.withdrawFromAccount(withdrawal);
    }

    @PostMapping("credit/")
    public ResponseEntity<Map<String, String>> creditAccount(@RequestBody @Valid Credit credit){
        return transactonService.creditAccount(credit);
    }

    @PostMapping("transfer/")
    public ResponseEntity<Map<String, String>> transfer(@RequestBody @Valid Transfer transfer){
        return transactonService.transferFromAccount(transfer);
    }

    @PostMapping("buy/")
    public ResponseEntity<Map<String, String>> purchaseItem(@RequestBody @Valid Purchase purchase){
        return transactonService.purchaseItem(purchase);
    }

}
