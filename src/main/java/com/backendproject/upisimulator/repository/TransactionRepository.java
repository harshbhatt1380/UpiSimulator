package com.backendproject.upisimulator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backendproject.upisimulator.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>
{
   Optional<Transaction> findBySenderIdAndIdempotencyKey(Integer upiId,String idempotencyKey);
   
   @Query("""
         SELECT t
         FROM Transaction t
         WHERE t.sender.bankAccount.user.id = :userId
            OR
               t.receiver.bankAccount.user.id = :userId
         """)
         List<Transaction>findTransactionsByUserId(Integer userId);
}
