package com.backendproject.upisimulator.repository;

import com.backendproject.upisimulator.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> 
{
    User findByEmail(String email);
    User findByContactNo(String contactNo);
}
