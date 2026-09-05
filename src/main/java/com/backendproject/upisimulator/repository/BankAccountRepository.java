package com.backendproject.upisimulator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.upisimulator.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> 
{
    List<BankAccount> findByUserEmail(String email);
    List<BankAccount> findByUserContactNo(String contactNo); 
} 
    
