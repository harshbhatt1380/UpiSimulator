package com.backendproject.upisimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.upisimulator.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> 
{
    
} 
    
