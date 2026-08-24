package com.backendproject.upisimulator.MyExceptions;

public class UserNotFoundException extends RuntimeException 
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
