package com.skorczewski.currencyConverter.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ExchangeRateRequest {
    private final Table table;
    private final CurrencyCode code;
    private final LocalDate startDate;
    private final LocalDate endDate;
}