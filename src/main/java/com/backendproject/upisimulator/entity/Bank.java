package com.backendproject.upisimulator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bank 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    

    @Column(unique = true,nullable = false)
    private String name;

    public Bank(String name)
    {
        this.name=name;
    }

    protected Bank()
    {

    }
    public String getName()
    {
        return name;
    }

    public Integer getId()
    {
        return id;
    }
}
