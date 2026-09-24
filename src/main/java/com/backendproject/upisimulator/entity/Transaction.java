package com.backendproject.upisimulator.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.backendproject.upisimulator.enumFolder.TStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Transaction 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne 
    @JoinColumn(name="sender_upi_id",nullable = false) 
    private Upi sender;

    @ManyToOne 
    @JoinColumn(name="receiver_upi_id",nullable = false)
    private Upi receiver;

    private BigDecimal amount;

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING)
    private TStatus status;

    @Column(unique = true,nullable = false)
    private String idempotencyKey;

    public Transaction(Upi sender,Upi receiver,BigDecimal amount,String idempotencyKey)
    {
        this.sender=sender;
        this.receiver=receiver;
        this.amount=amount;
        this.createdAt=LocalDateTime.now();
        this.status=TStatus.INITIATED;
        this.idempotencyKey=idempotencyKey;
    }

    protected Transaction()
    {

    }

    public void setIdempotencyKey(String idempotencyKey)
    {
        this.idempotencyKey=idempotencyKey;
    }

    public void setSender(Upi sender)
    {
        this.sender=sender;
    }

    public void setReceiver(Upi receiver)
    {
        this.receiver=receiver;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount=amount;
    }

    public void setStatus(TStatus status)
    {
        this.status=status;
    }

    public void setCompletedAt(LocalDateTime completedAt)
    {
        this.completedAt=completedAt;
    }

    public Upi getSender()
    {
        return sender;
    }

    public Upi getReceiver()
    {
        return receiver;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public TStatus getStatus()
    {
        return status;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public LocalDateTime getCompletedAt()
    {
        return completedAt;
    }

    public String getIdempotencyKey()
    {
        return idempotencyKey;
    }

}
