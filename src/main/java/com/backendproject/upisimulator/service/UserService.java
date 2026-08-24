package com.backendproject.upisimulator.service;

import com.backendproject.upisimulator.controller.UserController;
import org.springframework.stereotype.Service;

import com.backendproject.upisimulator.MyExceptions.ContactNoAlreadyTakenException;
import com.backendproject.upisimulator.MyExceptions.EmailAlreadyTakenException;
import com.backendproject.upisimulator.MyExceptions.UserNotFoundException;
import com.backendproject.upisimulator.dto.RequestDTO.UserRequestDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.UserResponseDTO;
import com.backendproject.upisimulator.repository.UserRepository;
import com.backendproject.upisimulator.user.User;


@Service
public class UserService 
{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, UserController userController)
    {
        this.userRepository=userRepository;
    }
    
    public UserResponseDTO createUser(UserRequestDTO user)
    {
        if(userRepository.findByEmail(user.getEmail())==null)
        {
            if(userRepository.findByContactNo(user.getContactNo())==null)
            {
                User userEntity = new User(user.getContactNo(), user.getEmail(), user.getName());
                userRepository.save(userEntity);
                return new UserResponseDTO(true,"user created successfully",userEntity.getCreatedAt(), userEntity.getName(), userEntity.getEmail(), userEntity.getContactNo());
            }
            else
            {
                throw new ContactNoAlreadyTakenException("Contact number already associated with another user, thus could not create user");
            }
        }
        else
        {
            throw new EmailAlreadyTakenException("Email already associated with another user, thus could not create user");
        }
    }

    UserResponseDTO findByEmail(String email)
    {
        User userEntity = userRepository.findByEmail(email);
        if(userEntity==null)
        {
            throw new UserNotFoundException("No user associated with given email thus searching user via email failed");
        }
        return new UserResponseDTO(true, "User found via search by Email", userEntity.getCreatedAt(), userEntity.getName(), userEntity.getEmail(), userEntity.getContactNo()); 
    }

    UserResponseDTO findByContactNo(String contactNo)
    {
        User userEntity = userRepository.findByContactNo(contactNo);
        if(userEntity==null)
        {
            throw new UserNotFoundException("No user associated with given contact number thus searching user via contact number failed");
        }
        return new UserResponseDTO(true, "User found via search by Contact Number", userEntity.getCreatedAt(), userEntity.getName(), userEntity.getEmail(), userEntity.getContactNo()); 
    }

    void updateName(String newUsername)
    {

    }

    void updateContactNo()
    {

    }

    void updateEmail()
    {

    }

    void deleteUser()
    {

    }     
}
