package com.backendproject.upisimulator.service;

import com.backendproject.upisimulator.entity.User;
import com.backendproject.upisimulator.repository.BankAccountRepository;
import com.backendproject.upisimulator.repository.BankRepository;
import com.backendproject.upisimulator.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.backendproject.upisimulator.MyExceptions.BankNotFoundException;
import com.backendproject.upisimulator.MyExceptions.InvalidCredentialException;
import com.backendproject.upisimulator.MyExceptions.UserNotFoundException;
import com.backendproject.upisimulator.dto.ResponseDTO.BankAccountResponseDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.BankAccountResponseListDTO;
import com.backendproject.upisimulator.entity.Bank;
import com.backendproject.upisimulator.entity.BankAccount;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService 
{
    private final BankAccountRepository bankAccountRepository;
    private final BankRepository bankRepository;
    private final UserRepository userRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository,BankRepository bankRepository,UserRepository userRepository)
    {
        this.bankAccountRepository=bankAccountRepository;
        this.bankRepository=bankRepository;
        this.userRepository=userRepository;
    }
    public BankAccountResponseDTO registerBankAccount(String bankName)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        Bank bank = bankRepository.findByName(bankName);

        if(bank==null)
        {
            throw new BankNotFoundException(bankName+" not registered on the app thus creation of bank account failed");
        }
        else
        {
            if(user==null)
            {
                throw new UserNotFoundException("No user account associated with the given email was found, thus creation of bank account failed");
            }
            else
            {
                BankAccount client = new BankAccount(user, bank);
                bankAccountRepository.save(client);
                return new BankAccountResponseDTO(client.getId(),client.getUser().getName(),client.getStatus(),client.getBalance(),client.getBank().getName());
            }
        }
    }  
    public List<BankAccountResponseListDTO> findBankAccounts()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if(user==null)
        {
            throw new UserNotFoundException("No user account associated with the given contact number was found, thus creation of bank account failed");
        }
        else
        {
            List<BankAccount> bankAccountList = bankAccountRepository.findByUserEmail(email);
            List<BankAccountResponseListDTO> response = new ArrayList<>();
            
            for(BankAccount item : bankAccountList)
            {
                response.add(new BankAccountResponseListDTO(item.getId(),item.getUser().getName(), item.getBank().getName(), item.getStatus()));
            }
            return response;
        }
    }

    public BankAccountResponseDTO findIndividualBankAccount(Integer id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if(bankAccountRepository.findByIdAndUserEmail(id, email).isEmpty())
        {
            throw new InvalidCredentialException("Invalid Id thus fetching bank account failed");
        }
        else
        {
            BankAccount bankAccount = bankAccountRepository.findByIdAndUserEmail(id, email).get();
            return new BankAccountResponseDTO(bankAccount.getId(), bankAccount.getUser().getEmail(), bankAccount.getStatus(), bankAccount.getBalance(), bankAccount.getBank().getName());
        }
    }
}  
