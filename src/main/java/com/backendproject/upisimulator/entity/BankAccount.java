package com.backendproject.upisimulator.entity;

import java.math.BigDecimal;

import com.backendproject.upisimulator.enumFolder.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class BankAccount 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_id",nullable = false)
    private Bank bank;

    @OneToOne(mappedBy = "bankAccount")
    private Upi upi;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal balance;

    public BankAccount(User user,Bank bank)
    {
        this.user=user;
        this.bank=bank;
        this.status=Status.ACTIVE;
        this.balance=BigDecimal.ZERO;
    }

    protected BankAccount()
    {

    }

    public void credit(BigDecimal amount)
    {
        balance=this.balance.add(amount);
    }

    public void debit(BigDecimal amount)
    {
        balance=this.balance.subtract(amount);
    }

    public void setUpi(Upi upi)
    {
        this.upi=upi;
    }

    public void setUser(User user)
    {
        this.user=user;
    }

    public void setBank(Bank bank)
    {
        this.bank=bank;
    }

    public void setStatus(Status status)
    {
        this.status=status;
    }

    public User getUser()
    {
        return user;
    }

    public Bank getBank()
    {
        return bank;
    }

    public Status getStatus()
    {
        return status;
    }

    public Integer getId()
    {
        return id;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public Upi getUpi()
    {
        return upi;
    }
}

