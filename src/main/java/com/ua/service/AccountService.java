package com.ua.service;

import com.ua.model.Status;
import com.ua.transport.input.AccountInputDto;
import com.ua.transport.output.AccountBalanceDto;

public interface AccountService {

    AccountBalanceDto checkBalance(String accountNumber);

    Status checkStatus(String accountNumber);

    AccountBalanceDto changeBalance(AccountInputDto requestBody);
}
