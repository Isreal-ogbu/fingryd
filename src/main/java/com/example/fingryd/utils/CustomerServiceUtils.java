package com.example.fingryd.utils;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.model_enum.AccountType;


public class CustomerServiceUtils {
    // Define default initial balances for account types
    public static final double SAVINGS_INITIAL_BALANCE = 0.0;
    public static final double CURRENT_INITIAL_BALANCE = 5000.0;
    public static final double FIXED_INITIAL_BALANCE = 50000.0;

    // Helper method to determine the initial balance based on account type
    public static double determineInitialBalance(AccountType accountType) {
        switch (accountType) {
            case SAVINGS:
                return SAVINGS_INITIAL_BALANCE;
            case CURRENT:
                return CURRENT_INITIAL_BALANCE;
            case FIXED:
                return FIXED_INITIAL_BALANCE;
            default:
                throw new CustomerException("Invalid account type.");
        }
    }
}
