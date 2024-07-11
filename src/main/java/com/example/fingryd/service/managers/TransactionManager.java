package com.example.fingryd.service.managers;

import com.example.fingryd.modelValidator.Credit;
import com.example.fingryd.modelValidator.Purchase;
import com.example.fingryd.modelValidator.Transfer;
import com.example.fingryd.modelValidator.Withdrawal;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TransactionManager {
    ResponseEntity<String> withdrawFromAccount(Withdrawal withdrawal);
    ResponseEntity<Map<String, String>> creditAccount(Credit credit);
    ResponseEntity<Map<String, String>> transferFromAccount(Transfer transfer);
    ResponseEntity<Map<String, String>> purchaseItem(Purchase purchase);
}
