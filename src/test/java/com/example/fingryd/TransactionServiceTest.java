package com.example.fingryd;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.Credit;
import com.example.fingryd.modelValidator.Withdrawal;
import com.example.fingryd.repository.CustomerAccountsRepository;
import com.example.fingryd.service.TransactonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactonService transactonService;

    @MockBean
    private CustomerAccountsRepository customerAccountsRepository;

    @BeforeEach
    public void setUp() {
        // You can initialize any necessary setup here
    }

    @Test
    public void testWithdrawFromAccountSuccessful() {
        // Arrange
        Withdrawal withdrawal = new Withdrawal("1234567890", 50.0, "1234");
        CustomerAccounts customerAccounts = new CustomerAccounts();
        customerAccounts.setPin(1234L);
        customerAccounts.setBalance(100.0);

        when(customerAccountsRepository.findByAccountNumber(1234567890L)).thenReturn(Optional.of(customerAccounts));

        // Act
        ResponseEntity<String> response = transactonService.withdrawFromAccount(withdrawal);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dear customer, a withdrawal of 50.0 was successfully deducted from your account", response.getBody());
        assertEquals(50.0, customerAccounts.getBalance());
        verify(customerAccountsRepository, times(1)).save(customerAccounts);
    }

    @Test
    public void testWithdrawFromAccountIncorrectPin() {
        // Arrange
        Withdrawal withdrawal = new Withdrawal("1234567890", 50.0, "5678");
        CustomerAccounts customerAccounts = new CustomerAccounts();
        customerAccounts.setPin(1234L);
        customerAccounts.setBalance(100.0);

        when(customerAccountsRepository.findByAccountNumber(1234567890L)).thenReturn(Optional.of(customerAccounts));

        // Act
        ResponseEntity<String> response = transactonService.withdrawFromAccount(withdrawal);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Incorrect pin. Check and Try again", response.getBody());
        assertEquals(100.0, customerAccounts.getBalance());
        verify(customerAccountsRepository, never()).save(any());
    }

    @Test
    public void testWithdrawFromAccountInsufficientBalance() {
        // Arrange
        Withdrawal withdrawal = new Withdrawal("1234567890", 200.0, "1234");
        CustomerAccounts customerAccounts = new CustomerAccounts();
        customerAccounts.setPin(1234L);
        customerAccounts.setBalance(100.0);

        when(customerAccountsRepository.findByAccountNumber(1234567890L)).thenReturn(Optional.of(customerAccounts));

        // Act
        ResponseEntity<String> response = transactonService.withdrawFromAccount(withdrawal);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Insufficient Balance", response.getBody());
        assertEquals(100.0, customerAccounts.getBalance());
        verify(customerAccountsRepository, never()).save(any());
    }

    @Test
    public void testCreditAccountSuccessful() {
        // Arrange
        Credit credit = new Credit("1234567890", 100.0);
        CustomerAccounts customerAccounts = new CustomerAccounts();
        customerAccounts.setAccountNumber(1234567890L); // Initialize the account number
        customerAccounts.setBalance(200.0);

        when(customerAccountsRepository.findByAccountNumber(1234567890L)).thenReturn(Optional.of(customerAccounts));

        // Act
        ResponseEntity<Map<String, String>> response = transactonService.creditAccount(credit);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        assertEquals("Success", response.getBody().get("message"));
        String actualMessage = response.getBody().get("Transaction message");
        String expectedMessage = "Successfully deposited 100.0 into account with account number 12345***";
        assertEquals(actualMessage, expectedMessage);

        verify(customerAccountsRepository, times(1)).save(customerAccounts);
    }


}
