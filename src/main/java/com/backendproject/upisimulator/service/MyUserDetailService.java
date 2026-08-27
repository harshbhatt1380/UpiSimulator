package com.backendproject.upisimulator.service;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.backendproject.upisimulator.MyExceptions.UserNotFoundException;
import com.backendproject.upisimulator.entity.User;
import com.backendproject.upisimulator.repository.UserRepository;
import com.backendproject.upisimulator.security.MyUserDetail;

@Service
public class MyUserDetailService 
{
    private final UserRepository userRepository;
    public MyUserDetailService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public MyUserDetail fetchUser(String email)
    {
        User user = userRepository.findByEmail(email);
        if(user==null)
        {
            throw new UserNotFoundException("Could not find user associated with given email thus authentication failed and exception thrown at MyUserDetailService");
        }
        else
        {
            Collection<GrantedAuthority> al= new ArrayList<>();
            al.add(new SimpleGrantedAuthority(user.getRole().name()));
            return new MyUserDetail(user.getEmail(), user.getPassword(),al);
        } 
    }
}
