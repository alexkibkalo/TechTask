package com.ua.utils;

import com.ua.model.Status;
import com.ua.service.AccountService;
import com.ua.transport.input.AccountInputDto;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class AccountStatusValidator implements ConstraintValidator<AccountStatusConstraint, AccountInputDto> {

    private final AccountService accountService;

    @Override
    public boolean isValid(AccountInputDto dto, ConstraintValidatorContext constraintValidatorContext) {
        return accountService.checkStatus(dto.getAccountNumber()).equals(Status.OPEN);
    }
}
