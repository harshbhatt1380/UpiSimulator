package com.backendproject.upisimulator.dto.ResponseDTO;

import java.time.LocalDateTime;

import com.backendproject.upisimulator.enumFolder.Role;

public class UserResponseDTO 
{
    private final String message;
    private final boolean success;
    private final LocalDateTime createdAt;
    private final String name;
    private final String email;
    private final String contactNo;
    private final Role role;

    public UserResponseDTO(boolean success,String message,Role role,LocalDateTime createdAt,String name,String email,String contactNo)
    {
        this.role=role;
        this.success=success;
        this.message=message;
        this.createdAt=createdAt;
        this.name=name;
        this.contactNo=contactNo;
        this.email=email;
    }

    public Role getRole()
    {
        return role;
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
