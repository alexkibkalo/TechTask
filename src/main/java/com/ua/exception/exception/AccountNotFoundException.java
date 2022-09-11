package com.ua.exception.exception;

import com.ua.exception.common.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(){
        super("Account not found");
    }
}
