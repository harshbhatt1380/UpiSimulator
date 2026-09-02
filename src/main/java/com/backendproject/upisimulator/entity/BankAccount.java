package com.backendproject.upisimulator.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class BankAccount 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Enumerated(EnumType.STRING)
    private String status;

    private BigDecimal balance;

    public BankAccount(User user,Bank bank,String status,BigDecimal balance)
    {
        this.user=user;
        this.bank=bank;
        this.status=status;
        this.balance=balance;
    }

    protected BankAccount()
    {

    }

    public void setUser(User user)
    {
        this.user=user;
    }

    public void setBank(Bank bank)
    {
        this.bank=bank;
    }

    public void setStatus(String status)
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

    public String getStatus()
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
}

