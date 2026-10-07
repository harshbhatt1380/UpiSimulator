package com.backendproject.upisimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.upisimulator.dto.ResponseDTO.BankAccountResponseDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.BankAccountResponseListDTO;
import com.backendproject.upisimulator.service.BankAccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/bankAccount")
@SecurityRequirement(name = "bearerAuth")
public class BankAccountController 
{
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService)
    {
        this.bankAccountService=bankAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<BankAccountResponseDTO> registerBankAccount(@RequestParam String bankName) 
    {
        BankAccountResponseDTO response = bankAccountService.registerBankAccount(bankName);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/credit")
    public ResponseEntity<BankAccountResponseDTO> creditBankAccount(@RequestParam String upiAddress,@RequestParam BigDecimal amount) 
    {
        BankAccountResponseDTO response = bankAccountService.credit(upiAddress, amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/debit")
    public ResponseEntity<BankAccountResponseDTO> debitBankAccount(@RequestParam String upiAddress,@RequestParam BigDecimal amount) 
    {
        BankAccountResponseDTO response = bankAccountService.debit(upiAddress, amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    @GetMapping("/getBankAccountList")
    public ResponseEntity<List<BankAccountResponseListDTO>> getBankAccountList() 
    {
        List<BankAccountResponseListDTO> result =  bankAccountService.findBankAccounts();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/getBankAccount")
    public ResponseEntity<BankAccountResponseDTO> getMethodName(@RequestParam Integer id) 
    {
        BankAccountResponseDTO result = bankAccountService.findIndividualBankAccount(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
