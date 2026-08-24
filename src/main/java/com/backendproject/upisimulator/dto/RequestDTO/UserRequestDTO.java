package com.backendproject.upisimulator.dto.RequestDTO;

import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO 
{
    //private Integer id;
    
    @NotBlank(message="Name cannot be blank")
    private String name;

    @NotBlank(message="Email cannot be blank")
    private String email;

    @NotBlank(message = "ContactNo cannot be blank")
    private String contactNo; 

    public UserRequestDTO(String name,String contactNo,String email)
    {
        this.contactNo=contactNo;
        this.name=name;
        this.email=email;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public void setContactNo(String contactNo)
    {
        this.contactNo=contactNo;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getContactNo()
    {
        return contactNo;
    }
}
