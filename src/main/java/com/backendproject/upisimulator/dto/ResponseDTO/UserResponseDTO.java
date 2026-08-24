package com.backendproject.upisimulator.dto.ResponseDTO;

import java.time.LocalDateTime;

public class UserResponseDTO 
{
    private final String message;
    private final boolean success;
    private final LocalDateTime createdAt;
    private final String name;
    private final String email;
    private final String contactNo;

    public UserResponseDTO(boolean success,String message,LocalDateTime createdAt,String name,String email,String contactNo)
    {
        this.success=success;
        this.message=message;
        this.createdAt=createdAt;
        this.name=name;
        this.contactNo=contactNo;
        this.email=email;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getContactNo()
    {
        return contactNo;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
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
