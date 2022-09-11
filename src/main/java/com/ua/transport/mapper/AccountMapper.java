package com.ua.transport.mapper;

import com.ua.model.entity.Account;
import com.ua.transport.output.AccountBalanceDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountBalanceDto mapToOutputDto(Account account){
        return AccountBalanceDto.builder()
                .balance(account.getBalance())
                .status(account.getStatus())
                .accountNumber(account.getAccountNumber())
                .currency(account.getCurrency())
                .build();
    }
}
