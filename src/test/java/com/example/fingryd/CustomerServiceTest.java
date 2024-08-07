package com.example.fingryd;

import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Customer;
import com.example.fingryd.model.CustomerAccounts;
import com.example.fingryd.modelValidator.Registration;
import com.example.fingryd.model.model_enum.AccountType;
import com.example.fingryd.repository.CustomerAccountsRepository;
import com.example.fingryd.repository.CustomerRepository;
import com.example.fingryd.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerAccountsRepository customerAccountsRepository;

    @Test
    public void testCreateCustomerAccount() {
        Registration registration = new Registration("John Doe", "+2348160006667", "john@example.com",
                "john doe", "password", "123 Main St", "1234", AccountType.SAVINGS);

        Customer customer = new Customer();

        customer.setName("John Doe");
        customer.setMobile("+2348160006667");
        customer.setEmail("john@example.com");
        customer.setUserName("john doe");
        customer.setPassword("password");
        customer.setAddress("123 Main St");

        CustomerAccounts customerAccounts = new CustomerAccounts(customer, "4567890", "1234", AccountType.SAVINGS);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerAccountsRepository.save(any(CustomerAccounts.class))).thenReturn(customerAccounts);

        ResponseEntity<Map<String, String>> response = customerService.createCustomerAccount(registration);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).containsKey("message"));
    }

    @Test
    public void testUpdateAccountInformation() {
        ResponseEntity<String> response = customerService.updateAccountInformation();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAccount() {
        Long customerId = 1L;
        Customer customer = new Customer();

        customer.setName("John Doe");
        customer.setMobile("+2348160006667");
        customer.setEmail("john@example.com");
        customer.setUserName("john doe");
        customer.setPassword("password");
        customer.setAddress("123 Main St");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> response = customerService.getAccount(customerId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("*******", Objects.requireNonNull(response.getBody()).getPassword());
    }


    @Test
    public void testGetAccountWithNonExistentId() {
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerException.class, () -> customerService.getAccount(customerId));
    }

    @Test
    public void testDeleteAccount() {
        Long customerId = 1L;
        ResponseEntity<String> response = customerService.deleteAccount(customerId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
