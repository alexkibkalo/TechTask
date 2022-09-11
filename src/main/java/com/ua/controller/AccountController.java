package com.ua.controller;

import com.ua.model.Status;
import com.ua.service.AccountService;
import com.ua.transport.input.AccountInputDto;
import com.ua.transport.output.AccountBalanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("{accountNumber}/balance")
    public AccountBalanceDto checkBalance(@PathVariable String accountNumber){
        return service.checkBalance(accountNumber);
    }

    @GetMapping("{accountNumber}/status")
    public Status checkStatus(@PathVariable String accountNumber){
        return service.checkStatus(accountNumber);
    }

    @PatchMapping("/change-balance")
    public AccountBalanceDto changeBalance(@Valid @RequestBody AccountInputDto requestBody){
        return service.changeBalance(requestBody);
    }
}
