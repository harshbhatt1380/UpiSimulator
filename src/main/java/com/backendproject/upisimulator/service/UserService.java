package com.backendproject.upisimulator.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backendproject.upisimulator.MyExceptions.ContactNoAlreadyTakenException;
import com.backendproject.upisimulator.MyExceptions.EmailAlreadyTakenException;
import com.backendproject.upisimulator.MyExceptions.InvalidCredentialException;
import com.backendproject.upisimulator.MyExceptions.UserNotFoundException;
import com.backendproject.upisimulator.dto.RequestDTO.UserRequestDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.UserResponseDTO;
import com.backendproject.upisimulator.entity.User;
import com.backendproject.upisimulator.repository.UserRepository;


@Service
public class UserService 
{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(JwtService jwtService,UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.jwtService=jwtService;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    
    public UserResponseDTO registerUser(UserRequestDTO user)
    {
        if(userRepository.findByEmail(user.getEmail())==null)
        {
            if(userRepository.findByContactNo(user.getContactNo())==null)
            {
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                User userEntity = new User(user.getContactNo(), user.getEmail(), user.getName(),hashedPassword);
                userRepository.save(userEntity);
                return new UserResponseDTO(true,"user created successfully",userEntity.getRole(),userEntity.getCreatedAt(), userEntity.getName(), userEntity.getEmail(), userEntity.getContactNo());
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

    public String login(String email,String password)
    {
        User user = userRepository.findByEmail(email);
        if(user==null)
        {
            throw new UserNotFoundException("No user with given email found thus login failed");
        }
        else
        {
            if(!passwordEncoder.matches(password,user.getPassword()))
            {
                throw new InvalidCredentialException("Incorrect password thus login failed");
            }
            else
            {
                return jwtService.generateToken(user.getEmail());
                
            }
        }
    }

    UserResponseDTO findByEmail(String email)
    {
        User userEntity = userRepository.findByEmail(email);
        if(userEntity==null)
        {
            throw new UserNotFoundException("No user associated with given email thus searching user via email failed");
        }
        return new UserResponseDTO(true, "User found via search by Email",userEntity.getRole(), userEntity.getCreatedAt(), userEntity.getName(), userEntity.getEmail(), userEntity.getContactNo()); 
    }

    UserResponseDTO findByContactNo(String contactNo)
    {
        User userEntity = userRepository.findByContactNo(contactNo);
        if(userEntity==null)
        {
            throw new UserNotFoundException("No user associated with given contact number thus searching user via contact number failed");
        }
        return new UserResponseDTO(true, "User found via search by Contact Number", userEntity.getRole(),userEntity.getCreatedAt(), userEntity.getName(), userEntity.getEmail(), userEntity.getContactNo()); 
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
