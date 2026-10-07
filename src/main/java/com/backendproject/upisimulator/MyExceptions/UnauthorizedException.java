package com.backendproject.upisimulator.MyExceptions;

public class UnauthorizedException extends RuntimeException
{
    public UnauthorizedException(String message)
    {
        super(message);
    }
}
