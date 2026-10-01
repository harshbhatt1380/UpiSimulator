package com.backendproject.upisimulator.dto.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.backendproject.upisimulator.enumFolder.TStatus;

public class TransactionDetailDTO 
{
    private final Integer id;
    private final String senderName;
    private final String receiverName;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;
    private final LocalDateTime completedAt;
    private final TStatus status;

    public TransactionDetailDTO(Integer id,String senderName,String receiverName,BigDecimal amount,LocalDateTime createdAt,LocalDateTime completedAt,TStatus status)
    {
        this.amount=amount;
        this.createdAt=createdAt;
        this.id=id;
        this.completedAt=completedAt;
        this.senderName=senderName;
        this.receiverName=receiverName;
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

    public String getSenderName()
    {
        return senderName;
    }

    public String getReceiverName()
    {
        return receiverName;
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
    
}
