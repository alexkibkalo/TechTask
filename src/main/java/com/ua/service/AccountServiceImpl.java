package com.ua.service;

import com.ua.exception.common.ExecutionFailed;
import com.ua.exception.exception.AccountNotFoundException;
import com.ua.model.Status;
import com.ua.model.entity.Account;
import com.ua.model.repository.AccountRepository;
import com.ua.transport.input.AccountInputDto;
import com.ua.transport.mapper.AccountMapper;
import com.ua.transport.output.AccountBalanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public AccountBalanceDto checkBalance(String accountNumber) {
        Account account = findByAccountNumber(accountNumber);
        return mapper.mapToOutputDto(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Status checkStatus(String accountNumber) {
        return findByAccountNumber(accountNumber).getStatus();
    }

    @Override
    public AccountBalanceDto changeBalance(AccountInputDto requestBody) {
        Account account = findByAccountNumber(requestBody.getAccountNumber());
        performBalanceOperation(account, requestBody);
        return mapper.mapToOutputDto(account);
    }

    private void performBalanceOperation(Account account, AccountInputDto requestBody) {
        if (!account.getCurrency().equals(requestBody.getCurrency())) {
            throw new ExecutionFailed("The transaction currency must match the account currency");
        }
        switch (requestBody.getOperationType()) {
            case CREDIT -> account.setBalance(account.getBalance() - requestBody.getAmount());
            case DEBIT -> account.setBalance(account.getBalance() + requestBody.getAmount());
        }
    }

    private Account findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);
    }
}
