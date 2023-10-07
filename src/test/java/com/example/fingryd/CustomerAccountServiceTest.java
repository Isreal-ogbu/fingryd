package com.example.fingryd;

import com.example.fingryd.model.Customer;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.AccountNumberValidator;
import com.example.fingryd.repository.CustomerAccountsRepository;
import com.example.fingryd.service.CustomerAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerAccountServiceTest {

    @Autowired
    private CustomerAccountService customerAccountService;

    @MockBean
    private CustomerAccountsRepository customerAccountsRepository;

    @Test
    public void testGetCustomerAccountByAccountNumber() {
        AccountNumberValidator accountNumberValidator = new AccountNumberValidator("1234567890");
        CustomerAccounts expectedAccount = new CustomerAccounts();
        Customer customer = new Customer();
        customer.setCustomerId(12345L);
        expectedAccount.setCustomerId(customer);

        when(customerAccountsRepository.findByAccountNumber(anyLong())).thenReturn(Optional.of(expectedAccount));

        ResponseEntity<CustomerAccounts> responseEntity = customerAccountService.getCustomerAccountByAccountNumber(accountNumberValidator);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
