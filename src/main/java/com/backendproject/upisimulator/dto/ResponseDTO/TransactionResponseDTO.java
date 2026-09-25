package com.backendproject.upisimulator.dto.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.backendproject.upisimulator.enumFolder.TStatus;

public class TransactionResponseDTO 
{
    private final Integer id;
    private final String senderUpiAddress;
    private final String receiverUpiAddress;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;
    private final LocalDateTime completedAt;
    private final TStatus status;
    private final String message;

    public TransactionResponseDTO(String message,Integer id,String senderUpiAddress,String receiverUpiAddress,BigDecimal amount,LocalDateTime createdAt,LocalDateTime completedAt,TStatus status)
    {
        this.message=message;
        this.amount=amount;
        this.createdAt=createdAt;
        this.id=id;
        this.completedAt=completedAt;
        this.senderUpiAddress=senderUpiAddress;
        this.receiverUpiAddress=receiverUpiAddress;
        this.status=status;
    }

    public Integer getId()
    {
        return id;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public String getSenderUpiAddress()
    {
        return senderUpiAddress;
    }

    public String getReceiverUpiAddress()
    {
        return receiverUpiAddress;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public LocalDateTime getCompletedAt()
    {
        return completedAt;
    }

    public TStatus getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }
}
