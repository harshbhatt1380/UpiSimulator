package com.backendproject.upisimulator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backendproject.upisimulator.dto.ResponseDTO.BankResponseDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.BankResponseListDTO;
import com.backendproject.upisimulator.entity.Bank;
import com.backendproject.upisimulator.repository.BankRepository;

@Service
public class BankService 
{
    private BankRepository bankRepository;
    public BankService(BankRepository bankRepository)
    {
        this.bankRepository=bankRepository;
    }
    public BankResponseDTO findBankByName(String name)
    {
        Bank bank = bankRepository.findByName(name);

        if(bank==null)
        {
            return new BankResponseDTO(false, name+" bank is not registered on the app","\0");
        }
        else
        {
            return new BankResponseDTO(true, name+" found on the app",bank.getName());
        }
    }

    public BankResponseListDTO findAllBanks()
    {
        List<Bank> bankList = new ArrayList<>();
        bankList=bankRepository.findAll();
        if(bankList.isEmpty())
        {
            return new BankResponseListDTO(false,"No banks registered on the app",bankList);
        }
        else
        {
            return new BankResponseListDTO(true,"Successfully fetched list of all banks",bankList);
        }
    }
}
