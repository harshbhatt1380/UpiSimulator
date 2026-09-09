package com.backendproject.upisimulator.MyExceptions;

public class BankNotFoundException extends RuntimeException
{
    public BankNotFoundException(String message)
    {
        super(message);
    }
}
