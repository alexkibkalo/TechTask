package com.ua.transport.input;

import com.ua.model.Currency;
import com.ua.model.OperationType;
import com.ua.utils.AccountStatusConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AccountStatusConstraint
public class AccountInputDto {

    @NotNull
    private OperationType operationType;

    @NotNull
    @NotBlank
    private String accountNumber;

    @NotNull
    private Currency currency;

    @NotNull
    private Double amount;
}
