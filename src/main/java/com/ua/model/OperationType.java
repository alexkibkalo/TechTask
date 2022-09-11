package com.ua.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationType {
    DEBIT("debit"),
    CREDIT("credit");

    private final String name;
}
