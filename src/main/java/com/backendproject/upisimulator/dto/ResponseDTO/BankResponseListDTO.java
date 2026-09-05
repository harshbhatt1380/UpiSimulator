package com.backendproject.upisimulator.dto.ResponseDTO;

import java.util.List;

import com.backendproject.upisimulator.entity.Bank;

public class BankResponseListDTO 
{
    private final boolean success;
    private final String message; 
    private final List<Bank> bankList;
    
    public BankResponseListDTO(boolean success,String message,List<Bank> bankList)
    {
        this.success=success;
        this.message=message;
        this.bankList=bankList;
    }
}
