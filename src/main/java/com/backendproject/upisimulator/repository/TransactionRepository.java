package com.backendproject.upisimulator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.upisimulator.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>
{
   Optional<Transaction> findBySenderIdAndIdempotencyKey(Integer upiId,String idempotencyKey);     
}
