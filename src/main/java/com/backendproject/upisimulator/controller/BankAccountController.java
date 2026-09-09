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

import java.util.List;

@RestController
@RequestMapping("/bankAccount")
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
