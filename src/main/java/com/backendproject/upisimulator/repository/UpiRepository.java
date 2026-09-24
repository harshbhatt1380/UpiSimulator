package com.backendproject.upisimulator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.upisimulator.entity.Upi;

public interface UpiRepository extends JpaRepository<Upi,Integer> 
{
    Optional<Upi> findByUpiAddress(String upiAddress);    
}
