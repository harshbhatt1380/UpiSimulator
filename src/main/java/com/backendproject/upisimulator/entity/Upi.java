package com.backendproject.upisimulator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity 
public class Upi 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne 
    @JoinColumn(name="bankAccount_id",nullable = false,unique = true)
    private BankAccount bankAccount;    

    @Column(unique = true,nullable = false)
    private String upiAddress;

    protected Upi()
    {
        
    }
    public Upi(BankAccount bankAccount)
    {
        this.bankAccount=bankAccount;
        this.upiAddress=bankAccount.getUser().getName()+bankAccount.getId()+"@"+bankAccount.getBank().getName();
    }

    public void setBankAccount(BankAccount bankAccount)
    {
        this.bankAccount=bankAccount;
    }

    public Integer getId()
    {
        return id;
    }

    public String getUpiAddress()
    {
        return upiAddress;
    }

    public BankAccount getBankAccount()
    {
        return bankAccount;
    }
}
