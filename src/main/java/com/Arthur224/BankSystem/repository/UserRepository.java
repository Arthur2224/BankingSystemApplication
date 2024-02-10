package com.Arthur224.BankSystem.repository;

import com.Arthur224.BankSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByAccountNumber(String accountNUmber);
    User findByAccountNumber(String accountNumber);
}
