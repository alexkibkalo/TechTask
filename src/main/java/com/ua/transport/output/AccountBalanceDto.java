package com.ua.transport.output;

import com.ua.model.Currency;
import com.ua.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceDto {

    private String accountNumber;

    private Currency currency;

    private Double balance;

    private Status status;

}
