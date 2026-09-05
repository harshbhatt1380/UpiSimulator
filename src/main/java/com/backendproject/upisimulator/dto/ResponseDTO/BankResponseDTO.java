package com.backendproject.upisimulator.dto.ResponseDTO;

public class BankResponseDTO 
{
    private final boolean success;
    private final String message;
    private final String bankName;

    public BankResponseDTO(boolean success,String message,String bankName)
    {
        this.success=success;
        this.message=message;
        this.bankName=bankName;
    } 
    
    public boolean isSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }
    
    public String getBankName()
    {
        return bankName;
    }
}
