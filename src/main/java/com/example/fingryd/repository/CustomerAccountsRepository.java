package com.example.fingryd.repository;

import com.example.fingryd.model.CustomerAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountsRepository extends JpaRepository<CustomerAccounts, Long> {
}
