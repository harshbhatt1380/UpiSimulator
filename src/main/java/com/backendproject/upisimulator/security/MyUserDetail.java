package com.backendproject.upisimulator.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetail implements UserDetails 
{
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> al;

    public MyUserDetail(String username,String password,Collection<? extends GrantedAuthority> al)
    {
        this.username=username;
        this.password=password;
        this.al=al;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public String getPassword()
    {
        return password;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return al;
    }
}
