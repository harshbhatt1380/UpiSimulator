package com.backendproject.upisimulator.dto.ResponseDTO;

public class ErrorResponse 
{
    private final boolean success;
    private final String message;
    
    public ErrorResponse(boolean success,String message)
    {
        this.success=success;
        this.message=message;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean isSuccess()
    {
        return success;
    }
}
