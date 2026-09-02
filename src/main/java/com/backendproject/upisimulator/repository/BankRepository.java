package com.backendproject.upisimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.upisimulator.entity.Bank;

public interface BankRepository extends JpaRepository<Bank,Integer>
{

} 
