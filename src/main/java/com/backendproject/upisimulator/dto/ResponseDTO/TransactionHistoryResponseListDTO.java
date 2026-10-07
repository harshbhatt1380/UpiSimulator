package com.backendproject.upisimulator.dto.ResponseDTO;

import java.util.ArrayList;


public class TransactionHistoryResponseListDTO 
{
    private String message;
    private boolean success;
    private ArrayList<TransactionDetailDTO> history;
    private int page,size,totalPages;
    private long totalElements;
    private boolean last;
    
    public TransactionHistoryResponseListDTO(String message,boolean success,ArrayList<TransactionDetailDTO> history,int page,int size,int totalPages,long totalElements,boolean last)
    {
        this.message=message;
        this.success=success;
        this.history=history;
        this.page=page;
        this.size=size;
        this.totalPages=totalPages;
        this.totalElements=totalElements;
        this.last=last;
    }

    public boolean isLast()
    {
        return last;
    }

    public String getMessage()
    {
        return message;
    }

    public int getPage()
    {
        return page;
    }

    public int getSize()
    {
        return size;
    }

    public long getTotalElements()
    {
        return totalElements;
    }

    public int getTotalPages()
    {
        return totalPages;
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
