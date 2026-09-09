package com.backendproject.upisimulator.dto.ResponseDTO;

import java.math.BigDecimal;

import com.backendproject.upisimulator.enumFolder.Status;

public class BankAccountResponseDTO 
{
    private final Integer accountNo;
    private final String holderName;
    private final Status status;
    private final BigDecimal balance;  
    private final String bankName;
    
    public BankAccountResponseDTO(Integer accountNo,String holderName,Status status,BigDecimal balance,String bankName)
    {
        this.accountNo=accountNo;
        this.holderName=holderName;
        this.status=status;
        this.balance=balance;
        this.bankName=bankName;
    }

    public Integer getAccountNo()
    {
        return accountNo;
    }

    public String getHolderName()
    {
        return holderName;
    }

    public Status getStatus()
    {
        return status;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public String getBankName()
    {
        return bankName;
    }
}
