package com.backendproject.upisimulator.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String contactNo;
    @Column(unique = true)
    private String email;
    private String name;
    private LocalDateTime createdAt;
    
    protected User()
    {

    }
    public User(String contactNo,String email,String name)
    {
        this.contactNo=contactNo;
        this.email=email;
        this.name=name;
        this.createdAt=LocalDateTime.now();
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

    public Integer getId()
    {
        return id;
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

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }
}
