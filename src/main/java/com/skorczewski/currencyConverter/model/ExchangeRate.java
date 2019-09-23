package com.skorczewski.currencyConverter.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExchangeRate {
    private String no;
    private LocalDate effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal bidDifferenceWithNextDay;
    private BigDecimal askDifferenceWithNextDay;
}