package com.backendproject.upisimulator.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.backendproject.upisimulator.MyExceptions.InvalidCredentialException;
import com.backendproject.upisimulator.dto.ResponseDTO.TransactionDetailDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.TransactionHistoryResponseListDTO;
import com.backendproject.upisimulator.dto.ResponseDTO.TransactionResponseDTO;
import com.backendproject.upisimulator.entity.BankAccount;
import com.backendproject.upisimulator.entity.Transaction;
import com.backendproject.upisimulator.entity.Upi;
import com.backendproject.upisimulator.entity.User;
import com.backendproject.upisimulator.enumFolder.Status;
import com.backendproject.upisimulator.enumFolder.TStatus;
import com.backendproject.upisimulator.repository.BankAccountRepository;
import com.backendproject.upisimulator.repository.TransactionRepository;
import com.backendproject.upisimulator.repository.UpiRepository;
import com.backendproject.upisimulator.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

@Service 
public class TransactionService 
{
    private final UpiRepository upiRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    public TransactionService(BankAccountRepository bankAccountRepository,UpiRepository upiRepository,TransactionRepository transactionRepository,UserRepository userRepository)
    {
        this.bankAccountRepository=bankAccountRepository;
        this.upiRepository=upiRepository;
        this.transactionRepository=transactionRepository;
        this.userRepository=userRepository;
    }
    
    @Transactional 
    public TransactionResponseDTO payment(String senderUpiAddress,String receiverUpiAddress,BigDecimal amount,String idempotencyKey)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        if(upiRepository.findByUpiAddress(senderUpiAddress).isPresent())
        {
            Upi senderUpi= upiRepository.findByUpiAddress(senderUpiAddress).get();
            BankAccount senderBankAccount = senderUpi.getBankAccount();
            if(senderBankAccount.getStatus()==Status.BLOCKED)
            {
                throw new InvalidCredentialException("Sender Bank Account is blocked, thus cannot proceed to make payment, kindly re-activate this account or use another bank account");
            }
            User sender = senderUpi.getBankAccount().getUser();
            if(!user.getId().equals(sender.getId()))
            {
                throw new InvalidCredentialException("You are not authenticated to send money from requested account thus transaction failed,kindly login from the requested account to carry out the transaction");
            }
            else
            {
                if(upiRepository.findByUpiAddress(receiverUpiAddress).isPresent())
                {
                    Upi receiverUpi = upiRepository.findByUpiAddress(receiverUpiAddress).get();
                    BankAccount receiverBankAccount = receiverUpi.getBankAccount();
                    Integer senderBankAccountId=senderBankAccount.getId();
                    Integer receiverBankAccountId=receiverBankAccount.getId();
                    if(senderBankAccountId<receiverBankAccountId)
                    {
                        senderBankAccount=bankAccountRepository.findById(senderBankAccountId).get();
                        receiverBankAccount=bankAccountRepository.findById(receiverBankAccountId).get();
                    }
                    else
                    {
                        receiverBankAccount=bankAccountRepository.findById(receiverBankAccountId).get();
                        senderBankAccount=bankAccountRepository.findById(senderBankAccountId).get();
                    }
                    if(sender.getId().equals(receiverBankAccount.getUser().getId()))
                    {
                        throw new InvalidCredentialException("Sender and Receiver cannot be same");
                    }
                    else if(receiverBankAccount.getStatus()==Status.BLOCKED)
                    {
                        throw new InvalidCredentialException("Receiver Bank Account is blocked, thus cannot proceed to make payment, kindly re-activate this account or use another bank account");
                    }
                    else if(transactionRepository.findBySenderIdAndIdempotencyKey(senderUpi.getId(),idempotencyKey).isPresent())
                    {
                        Transaction transaction = transactionRepository.findBySenderIdAndIdempotencyKey(senderUpi.getId(),idempotencyKey).get();
                        return new TransactionResponseDTO("Idempotency Key already existed, thus here is the transaction report",transaction.getId(), transaction.getSender().getUpiAddress(), transaction.getReceiver().getUpiAddress(), transaction.getAmount(), transaction.getCreatedAt(), transaction.getCompletedAt(), transaction.getStatus());
                    }
                    else if(amount.compareTo(BigDecimal.ZERO)<1)
                    {
                        throw new InvalidCredentialException("Transaction amount cannot be less than or equal to 0,thus transaction failed");
                    }
                    else if(senderBankAccount.getBalance().compareTo(amount)<0)
                    {
                        throw new InvalidCredentialException("Insufficient funds, the amount to be paid is grater than the amount present in your bank account thus the transaction failed");
                    }
                    else
                    {
                        Transaction transaction = new Transaction(senderUpi, receiverUpi, amount, idempotencyKey);
                        transaction.setStatus(TStatus.PROCESSING);
                        transactionRepository.save(transaction);
                        senderBankAccount.debit(amount);
                        receiverBankAccount.credit(amount);
                        transaction.setStatus(TStatus.SUCCESS);
                        transaction.setCompletedAt(LocalDateTime.now());
                        return new TransactionResponseDTO("Transaction completed successfully",transaction.getId(), transaction.getSender().getUpiAddress(), transaction.getReceiver().getUpiAddress(), transaction.getAmount(), transaction.getCreatedAt(), transaction.getCompletedAt(), transaction.getStatus());
                    }
                }
                else
                {
                    throw new InvalidCredentialException("Invalid Receiver Upi address thus transaction failed, please check the provided Upi address again");        
                }
            }
        }
        else
        {
            throw new InvalidCredentialException("Invalid Sender Upi address thus transaction failed, please check the provided Upi address again");
        }
    }
    public TransactionHistoryResponseListDTO transactionHistory(int page,int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User authUser = userRepository.findByEmail(email);

        Page<Transaction> transactionList=transactionRepository.findTransactionsByUserId(authUser.getId(),pageable);

        if(transactionRepository.findTransactionsByUserId(authUser.getId(),pageable).isEmpty())
        {
            return new TransactionHistoryResponseListDTO("No transaction history exists", true, null,0,0,0,0,true); 
        }
        else
        {
            ArrayList<TransactionDetailDTO> response = new ArrayList<>();
            for(Transaction item : transactionList.getContent())
            {

                response.add(new TransactionDetailDTO(item.getId(),item.getSender().getBankAccount().getUser().getName() ,item.getReceiver().getBankAccount().getUser().getName(),item.getAmount() ,item.getCreatedAt() ,item.getCompletedAt() ,item.getStatus()));
            }
            return new TransactionHistoryResponseListDTO ("Transaction history fetched successfully", true, response,transactionList.getNumber(),transactionList.getSize(),transactionList.getTotalPages(),transactionList.getTotalElements(),transactionList.isLast());
        }
    }

    public TransactionDetailDTO individualTransaction(Integer id)
    {
        if(transactionRepository.findById(id).isEmpty())
        {
            throw new InvalidCredentialException("No transaction with given id present in database");
        }
        else
        {
            
            Transaction t = transactionRepository.findById(id).get();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            User authUser = userRepository.findByEmail(email);
            User sender = t.getSender().getBankAccount().getUser();
            User receiver = t.getReceiver().getBankAccount().getUser();
            if(authUser.getId().equals(sender.getId()) || authUser.getId().equals(receiver.getId()))
            {
                return new TransactionDetailDTO(t.getId(), sender.getName(), receiver.getName(), t.getAmount(), t.getCreatedAt(), t.getCompletedAt(), t.getStatus());
            }
            else
            {
                throw new InvalidCredentialException("Unauthorized access, please login with sender's or receiver's account to access the given transaction data");
            }
        }
    }
}
