package com.backendproject.upisimulator.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.upisimulator.dto.ResponseDTO.TransactionResponseDTO;
import com.backendproject.upisimulator.service.TransactionService;

@RestController
@RequestMapping("/transactions") 
public class TransactionController 
{
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService)
    {
        this.transactionService=transactionService;
    }

    @PostMapping("/pay")
    public ResponseEntity<TransactionResponseDTO> makePayment(@RequestParam String senderUpiAddress,@RequestParam String receiverUpiAddress,@RequestParam BigDecimal amount,@RequestParam  String idempotencyKey)
    {
        TransactionResponseDTO response = transactionService.payment(senderUpiAddress, receiverUpiAddress, amount, idempotencyKey);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }    
}
