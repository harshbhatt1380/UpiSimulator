package com.backendproject.upisimulator.dto.ResponseDTO;

import com.backendproject.upisimulator.enumFolder.Status;

public class BankAccountResponseListDTO 
{
    private final Integer accountNo;
    private final String holderName;
    private final String bankName;
    private final Status status;
    
    public BankAccountResponseListDTO(Integer accountNo,String holderName,String bankName,Status status)
    {
        this.accountNo=accountNo;
        this.holderName=holderName;
        this.bankName=bankName;
        this.status=status;
    }

    public Integer getAccountNo()
    {
        return accountNo;
    }

    public String getHolderName()
    {
        return holderName;
    }

    public String getBankName()
    {
        return bankName;
    }

    public Status getStatus()
    {
        return status;
    }
}
