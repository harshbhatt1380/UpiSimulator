package com.backendproject.upisimulator.MyExceptions;

public class InvalidCredentialException extends RuntimeException 
{
    public InvalidCredentialException(String message)
    {
        super(message);
    }
}
