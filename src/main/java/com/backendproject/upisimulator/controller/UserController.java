package com.backendproject.upisimulator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.upisimulator.dto.RequestDTO.UserRequestDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.LoginResponse;
import com.backendproject.upisimulator.dto.ResponseDTO.UserResponseDTO;
import com.backendproject.upisimulator.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/users")
public class UserController 
{
    private final UserService userService;
    
    public UserController(UserService userService)
    {
        this.userService=userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO user) 
    {

        UserResponseDTO result =  userService.registerUser(user);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserRequestDTO user) 
    {
        String token = userService.login(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String getTestResults() 
    {
        return "Success";
    }
    
}
