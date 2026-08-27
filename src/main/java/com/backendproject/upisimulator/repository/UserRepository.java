package com.backendproject.upisimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.upisimulator.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> 
{
    User findByEmail(String email);
    User findByContactNo(String contactNo);
}
