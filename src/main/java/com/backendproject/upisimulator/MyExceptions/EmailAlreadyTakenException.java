package com.backendproject.upisimulator.MyExceptions;

public class EmailAlreadyTakenException extends RuntimeException 
{
    public EmailAlreadyTakenException(String message)
    {
        super(message);
    } 
}
