package com.backendproject.upisimulator.MyExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backendproject.upisimulator.dto.ResponseDTO.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
    @ExceptionHandler(value=InvalidCredentialException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleInvalidCredential(InvalidCredentialException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }
    
    @ExceptionHandler(value=UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }
    
    @ExceptionHandler(value=ContactNoAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleContactNoAlreadyTaken(ContactNoAlreadyTakenException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }

    @ExceptionHandler(value=EmailAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEmailAlreadyTaken(EmailAlreadyTakenException ex)
    {
        return new ErrorResponse(false, ex.getMessage());
    }

    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMthodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        String fieldName = ex.getBindingResult().getFieldError().getField();
        String errorMessage = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        return new ErrorResponse(false, fieldName+" : "+errorMessage);
    }
}
