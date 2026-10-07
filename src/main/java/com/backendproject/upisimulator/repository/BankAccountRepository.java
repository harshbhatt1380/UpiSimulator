package com.backendproject.upisimulator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.backendproject.upisimulator.entity.BankAccount;

import jakarta.persistence.LockModeType;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer> 
{
    List<BankAccount> findByUserEmail(String email);
    
    List<BankAccount> findByUserContactNo(String contactNo);

    Optional<BankAccount> findByIdAndUserEmail(Integer id,String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<BankAccount>findById(Integer id);
} 
    
