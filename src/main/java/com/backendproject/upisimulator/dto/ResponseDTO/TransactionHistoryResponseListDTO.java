package com.backendproject.upisimulator.dto.ResponseDTO;

import java.util.ArrayList;


public class TransactionHistoryResponseListDTO 
{
    private String message;
    private boolean success;
    private ArrayList<TransactionDetailDTO> history;
    
    public TransactionHistoryResponseListDTO(String message,boolean success,ArrayList<TransactionDetailDTO> history)
    {
        this.message=message;
        this.success=success;
        this.history=history;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public ArrayList<TransactionDetailDTO> getHistory()
    {
        return history;
    }
}
