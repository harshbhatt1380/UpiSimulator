package com.backendproject.upisimulator.MyExceptions;

public class ContactNoAlreadyTakenException extends RuntimeException 
{
    public ContactNoAlreadyTakenException(String message)
    {
        super(message);
    }
}
