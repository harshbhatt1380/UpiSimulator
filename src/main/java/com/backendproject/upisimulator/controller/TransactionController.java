package com.backendproject.upisimulator.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.upisimulator.dto.ResponseDTO.TransactionDetailDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.TransactionHistoryResponseListDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.TransactionResponseDTO;
import com.backendproject.upisimulator.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/transactions") 
@SecurityRequirement(name = "bearerAuth")
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
    @GetMapping("/transactionHistory")
    public ResponseEntity<TransactionHistoryResponseListDTO> getAllTransactions(@RequestParam int page,@RequestParam int size) 
    {
        TransactionHistoryResponseListDTO response = transactionService.transactionHistory(page,size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transactionWithId")
    public ResponseEntity<TransactionDetailDTO> getTransaction(@RequestParam Integer id) 
    {
        TransactionDetailDTO response = transactionService.individualTransaction(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
