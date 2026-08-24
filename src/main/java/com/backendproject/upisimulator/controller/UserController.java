package com.backendproject.upisimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.upisimulator.dto.RequestDTO.UserRequestDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.UserResponseDTO;
import com.backendproject.upisimulator.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController 
{
    private final UserService userService;
    
    public UserController(UserService userService)
    {
        this.userService=userService;
    }
    
    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO user) 
    {

        UserResponseDTO result =  userService.createUser(user);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }
    
}
